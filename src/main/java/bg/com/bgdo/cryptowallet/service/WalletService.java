package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Asset;
import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.model.Wallet;
import bg.com.bgdo.cryptowallet.shared.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WalletService {

  private final BrokerService brokerService;
  private final TradeService tradeService;

  public Wallet getWallet() {
    Set<String> exchanges = getExchanges();
    List<Wallet> exchangeWallets = exchanges.stream().map(e -> getWallet(e)).collect(Collectors.toList());
    return getWallet(exchangeWallets);
  }

  public Wallet getWallet(String exchange) {
    return getWallet(exchange, null);
  }

  public Wallet getWallet(List<Wallet> exchangeWallets) {
    return getWallet(null, exchangeWallets);
  }

  public Wallet getWallet(String exchange, List<Wallet> exchangeWallets) {
    BigDecimal usdtbrl = brokerService.getAssetLastPrice("USDTBRL");
    return new Wallet(getAssets(exchange), usdtbrl, exchangeWallets, exchange);
  }

  public List<Asset> getAssets(String exchange) {
    final List<Trade> trades = tradeService.findAll(null);
    final Map<String, List<Trade>> tradesMap = trades.stream()
      .filter(trade -> Objects.nonNull(exchange) ? exchange.equals(trade.getExchange()) : true)
      .collect(Collectors.groupingBy(Trade::getTicker));

    final List<Asset> list = tradesMap.entrySet().stream()
      .map(asset -> {
        final BigDecimal assetLastPrice = brokerService.getAssetLastPrice(asset.getKey());
        return new Asset(asset.getKey(), getQuantity(asset.getValue()), getAveragePrice(asset.getValue()), assetLastPrice);
      })
      .sorted(Comparator.comparing(Asset::getActualValue).reversed())
      .collect(Collectors.toList());

    return list;
  }

  public Set<String> getExchanges() {
    final List<Trade> trades = tradeService.findAll(null);
    return trades.stream().map(t -> t.getExchange()).collect(Collectors.toSet());
  }

  public BigDecimal getAveragePrice(List<Trade> trades) {
    if (trades == null) {
      return BigDecimal.ZERO;
    }
    BigDecimal valorTotal = BigDecimal.ZERO;
    BigDecimal quantityTotal = BigDecimal.ZERO;

    for (Trade trade : trades) {
      valorTotal = valorTotal.add((trade.getPrice().multiply(trade.getAmount())));
      quantityTotal = quantityTotal.add(trade.getAmount());
    }
    return valorTotal.divide(quantityTotal, Constants.PRICE_SCALE, RoundingMode.FLOOR);
  }

  public BigDecimal getQuantity(List<Trade> trades) {
    return trades.stream().map(Trade::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
