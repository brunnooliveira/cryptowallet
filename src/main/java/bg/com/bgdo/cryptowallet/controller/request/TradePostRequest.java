package bg.com.bgdo.cryptowallet.controller.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradePostRequest {

  @NotNull
  private Long date;
  @NotNull
  @NotBlank
  private String asset;
  @NotNull
  @NotBlank
  private String operationType;
  @NotNull
  @Positive
  private BigDecimal price;
  @NotNull
  @Positive
  private BigDecimal quantity;

}
