package bg.com.bgdo.cryptowallet.controller;

import bg.com.bgdo.cryptowallet.controller.mapper.TradeMapper;
import bg.com.bgdo.cryptowallet.controller.request.TradePostRequest;
import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.service.TradeService;
import bg.com.bgdo.cryptowallet.shared.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Constants.API_URL_BASE_PRIVATE + "/trades")
public class TradeController {

  private final TradeService tradeService;
  private final TradeMapper tradeMapper;

  @PostMapping
  public ResponseEntity newTrade(@RequestBody @Valid TradePostRequest tradePostRequest) throws JsonProcessingException {
    Trade trade = tradeMapper.tradePostRequestToTrade(tradePostRequest);
    tradeService.save(trade);
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(tradePostRequest));
  }
  
}
