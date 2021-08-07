package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Asset;
import bg.com.bgdo.cryptowallet.model.Trade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WalletService {

//    private final BrokerService brokerService;
    private final TradeService tradeService;

    public List<Asset> getWallet() {
        final List<Trade> trades = tradeService.findAll(null);
        System.out.println(trades);
        final Map<String, List<Trade>> tradesMap = trades.stream().collect(Collectors.groupingBy(Trade::getAsset));
        final List<Asset> list = tradesMap.entrySet().stream()
                .map(asset -> new Asset(asset.getKey(), getQuantity(asset.getValue()), getAveragePrice(asset.getValue())))
                .collect(Collectors.toList());
        return list;
    }

    public BigDecimal getAveragePrice(List<Trade> trades) {
        if( trades == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal valorTotal = BigDecimal.ZERO;
        BigDecimal quantityTotal = BigDecimal.ZERO;

        for (Trade trade : trades) {
            valorTotal = valorTotal.add((trade.getPrice().multiply(trade.getQuantity())));
            quantityTotal = quantityTotal.add(trade.getQuantity());
        }

        return valorTotal.divide(quantityTotal);
    }

    public BigDecimal getQuantity(List<Trade> trades) {
        return trades.stream().map(Trade::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
