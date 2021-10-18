package bg.com.bgdo.cryptowallet.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradePutRequest {

  @NotNull
  @NotBlank
  private String id;
  @NotNull
  private Long date;
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
