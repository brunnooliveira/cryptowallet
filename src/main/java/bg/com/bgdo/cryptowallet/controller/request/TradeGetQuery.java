package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TradeGetQuery {
    private Long date;
    private String ticker;
    private String operationType;
}
