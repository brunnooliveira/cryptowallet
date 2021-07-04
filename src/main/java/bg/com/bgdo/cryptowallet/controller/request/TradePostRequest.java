package bg.com.bgdo.cryptowallet.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
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
