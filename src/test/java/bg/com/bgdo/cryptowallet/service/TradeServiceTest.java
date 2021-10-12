package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.repository.TradeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TradeServiceTest {

  @InjectMocks
  private TradeService tradeService;

  @Mock
  private TradeRepository tradeRepository;
  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldPopulateTradeIdWhenSaved() {
    when(tradeRepository.save(any())).thenReturn(null);

    Trade trade = new Trade();
    Trade savedTrade = tradeService.save(trade);
    assertThat(savedTrade.getId()).isNotEmpty();
  }

  @Test
  void shouldReturnTradeListOnFindAllByExample() {
    when(tradeRepository.findAll(any(Example.class)))
      .thenReturn(Arrays.asList(new Trade(), new Trade()));

    List<Trade> trades = tradeService.findAll(null);
    assertThat(trades.size()).isEqualTo(2);
  }

  @Test
  void shouldReturnTradeListOnFindAllByExamplePageable() {
    PageImpl<Trade> tradePage = new PageImpl<>(Arrays.asList(new Trade(), new Trade()));
    when(tradeRepository.findAll(any(Example.class), any(Pageable.class)))
      .thenReturn(tradePage);

    Page<Trade> pageResult = tradeService.findAllPageable(PageRequest.of(1,1), new Trade());
    assertThat(pageResult.getTotalElements()).isEqualTo(2);
  }

}