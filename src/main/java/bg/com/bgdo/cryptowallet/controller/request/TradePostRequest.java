package bg.com.bgdo.cryptowallet.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradePostRequest {

  @NotNull
  private LocalDate date;
  @NotNull
  @NotBlank
  private String ticker;
  @NotNull
  @NotBlank
  private String operationType;
  @NotNull
  @Positive
  private BigDecimal price;
  @NotNull
  @Positive
  private BigDecimal amount;
  @NotNull
  @NotBlank
  private String exchange;
}
