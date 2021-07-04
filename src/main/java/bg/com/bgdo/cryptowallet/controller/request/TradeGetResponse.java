package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TradeGetResponse {
    private Long date;
    private String asset;
    private String operationType;
    private BigDecimal price;
    private BigDecimal quantity;
}
