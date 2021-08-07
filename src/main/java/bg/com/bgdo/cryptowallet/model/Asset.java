package bg.com.bgdo.cryptowallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Asset {

    private String asset;
    private BigDecimal quantity;
    private BigDecimal price;

}
