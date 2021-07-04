package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeGetQuery {
    private Long date;
    private String asset;
    private String operationType;
}
