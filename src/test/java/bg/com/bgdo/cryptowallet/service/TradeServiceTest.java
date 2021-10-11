package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.repository.TradeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

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
  void shouldReturnTradeListOnFindAll() {
    when(tradeRepository.findAll(any(Example.class)))
      .thenReturn(Arrays.asList(new Trade(), new Trade()));

    List<Trade> trades = tradeService.findAll(null);
    assertThat(trades.size()).isEqualTo(2);

  }


}