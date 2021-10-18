package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TradeGetResponse {
    private String id;
    private Long date;
    private String ticker;
    private String operationType;
    private BigDecimal price;
    private BigDecimal amount;
    private String exchange;
}
