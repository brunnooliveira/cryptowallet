package bg.com.bgdo.cryptowallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@ToString
@Getter
@AllArgsConstructor
public class Asset {

    private String ticker;
    private BigDecimal amount;
    private BigDecimal price;
    private BigDecimal actualPrice;

    public BigDecimal getTotalPaid(){
        return amount.multiply(price);
    }

    public BigDecimal getActualValue(){
        return Objects.nonNull(actualPrice) ? amount.multiply(actualPrice) : BigDecimal.ZERO;
    }

    public BigDecimal getProfitability(){
        return getActualValue().multiply(BigDecimal.valueOf(100.0))
          .divide(getTotalPaid(), 2, RoundingMode.FLOOR)
          .subtract(BigDecimal.valueOf(100.0));
    }

}
