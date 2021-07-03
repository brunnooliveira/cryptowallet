package bg.com.bgdo.cryptowallet.controller.request;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import bg.com.bgdo.cryptowallet.model.Trade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

  @JsonIgnore
  public Trade getTrade(){
    Trade trade = new Trade();
    trade.setDate(Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate());
    trade.setAsset(asset);
    trade.setOperationType(operationType);
    trade.setPrice(price);
    trade.setQuantity(quantity);
    return trade;
  }

}
