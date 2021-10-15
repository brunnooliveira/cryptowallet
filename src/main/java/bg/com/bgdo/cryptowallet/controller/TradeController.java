package bg.com.bgdo.cryptowallet.controller;

import bg.com.bgdo.cryptowallet.controller.mapper.TradeMapper;
import bg.com.bgdo.cryptowallet.controller.request.TradeGetQuery;
import bg.com.bgdo.cryptowallet.controller.request.TradeGetResponse;
import bg.com.bgdo.cryptowallet.controller.request.TradePostRequest;
import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.service.TradeService;
import bg.com.bgdo.cryptowallet.shared.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Constants.URL_API_BASE_PRIVATE + "/trades")
public class TradeController {

  private final TradeService tradeService;
  private final TradeMapper tradeMapper;
  private final ObjectMapper objectMapper;

  @PostMapping
  public ResponseEntity newTrade(@RequestBody @Valid TradePostRequest tradePostRequest) throws JsonProcessingException {
    Trade trade = tradeMapper.tradePostRequestToTrade(tradePostRequest);
    tradeService.save(trade);
    return ResponseEntity.ok(objectMapper.writeValueAsString(tradePostRequest));
  }

  @GetMapping
  public ResponseEntity<List<TradeGetResponse>> getTrades(TradeGetQuery tradeGetQuery) {
    Trade tradeQuery = tradeMapper.tradeGetQueryToTrade(tradeGetQuery);
    List<Trade> trades = tradeService.findAll(tradeQuery);
    List<TradeGetResponse> tradesGetResponse = trades.stream().map(trade -> tradeMapper.tradeToTradeGetResponse(trade)).collect(Collectors.toList());
    return ResponseEntity.ok(tradesGetResponse);
  }

//  @GetMapping
  public ResponseEntity<Page<TradeGetResponse>> getTrades(Pageable pageable, TradeGetQuery tradeGetQuery) {
    Trade tradeQuery = tradeMapper.tradeGetQueryToTrade(tradeGetQuery);
    Page<Trade> trades = tradeService.findAllPageable(pageable, tradeQuery);
    Page<TradeGetResponse> tradesGetResponse = trades.map(trade -> tradeMapper.tradeToTradeGetResponse(trade));
    return ResponseEntity.ok(tradesGetResponse);
  }
  
}
