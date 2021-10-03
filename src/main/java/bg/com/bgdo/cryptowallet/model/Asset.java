package bg.com.bgdo.cryptowallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Asset {

    private String ticker;
    private BigDecimal amount;
    private BigDecimal price;
    private BigDecimal actualPrice;

}
