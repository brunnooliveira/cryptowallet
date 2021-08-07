package bg.com.bgdo.cryptowallet.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Document(collection = "trades")
public class Trade {

    @Id
    private String id;
    @NotNull
    private LocalDate date;
    @NotNull
    @NotBlank
    private String asset;
    @NotNull
    @NotBlank
    private OperationType operationType;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    @Positive
    private BigDecimal quantity;

}
