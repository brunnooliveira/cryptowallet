package bg.com.bgdo.cryptowallet.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
public class Wallet {
  private final List<Asset> assets;
  private final BigDecimal usdtBrlQuote;
  private final List<Wallet> exchangeWallets;
  private final String exchange;


  public List<Asset> getAssets() {
    return assets;
  }

  public BigDecimal getTotalPaid() {
    if (assets != null) {
      return assets.stream()
        .map(asset -> asset.getTotalPaid())
        .reduce((acum, value) -> acum.add(value))
        .orElse(BigDecimal.ZERO);
    }
    return BigDecimal.ZERO;
  }

  public BigDecimal getActualValue() {
    if (assets != null) {
      return assets.stream()
        .map(asset -> asset.getActualValue())
        .reduce((acum, value) -> acum.add(value))
        .orElse(BigDecimal.ZERO);
    }
    return BigDecimal.ZERO;
  }

  public BigDecimal getProfitability() {
    return getActualValue().multiply(BigDecimal.valueOf(100.0))
      .divide(getTotalPaid(), 2, RoundingMode.FLOOR)
      .subtract(BigDecimal.valueOf(100.0));
  }

  public BigDecimal getActualValueBRL(){
    return getActualValue().multiply(usdtBrlQuote);
  }

  public List<Wallet> getExchangeWallets(){
    return exchangeWallets;
  }

  public String getExchange() {
    return exchange;
  }
}
