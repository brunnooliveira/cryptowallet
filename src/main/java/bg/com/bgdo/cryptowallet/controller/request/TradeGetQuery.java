package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TradeGetQuery {
    private LocalDate date;
    private String ticker;
    private String operationType;
}
