package bg.com.bgdo.cryptowallet.controller;

import javax.validation.Valid;

import bg.com.bgdo.cryptowallet.service.TradeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.com.bgdo.cryptowallet.controller.request.TradePostRequest;
import bg.com.bgdo.cryptowallet.shared.Constants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Constants.API_URL_BASE_PRIVATE + "/trades")
public class TradeController {

  private final TradeService tradeService;

  @PostMapping
  public ResponseEntity newTrade(@RequestBody @Valid TradePostRequest tradePostRequest) throws JsonProcessingException {
    log.info(new ObjectMapper().writeValueAsString(tradePostRequest));
    tradeService.save(tradePostRequest.getTrade());
    return ResponseEntity.ok(new ObjectMapper().writeValueAsString(tradePostRequest));
  }
  
}
