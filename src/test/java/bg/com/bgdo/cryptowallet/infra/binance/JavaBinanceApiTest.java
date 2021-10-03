package bg.com.bgdo.cryptowallet.infra.binance;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.service.WalletService;
import bg.com.bgdo.cryptowallet.shared.Constants;
import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JavaBinanceApiTest {

    @InjectMocks
    private WalletService walletService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnCandleListFromSymbol(){
//        final BigDecimal quota = BigDecimal.valueOf(40.0);
        final BigDecimal quota = BigDecimal.valueOf(80.0);
//        final BigDecimal quota = BigDecimal.valueOf(5.71);
//        final BigDecimal quota = BigDecimal.valueOf(11.42);

        final int bars = 142; //Weeks
//        final int bars = 1000; //Days

//        final String ticker = "BTCUSDT";
        final String ticker = "ETHUSDT";

        final boolean onlyRedBars = true;


        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
        BinanceApiRestClient client = factory.newRestClient();
        final List<Candlestick> btcusdt =
          client.getCandlestickBars(ticker, CandlestickInterval.WEEKLY, bars, null, null)
            .stream()
            .filter(c -> !onlyRedBars || Double.valueOf(c.getClose()) < Double.valueOf(c.getOpen()))
            .collect(Collectors.toList());
//        btcusdt.forEach(c -> log.info(c.toString()));


        final List<Trade> trades = btcusdt.stream()
          .map(c -> {
            Trade trade1 = new Trade();
            trade1.setTicker("BTCUSD");
            trade1.setAmmount(quota.divide(new BigDecimal(c.getClose()), Constants.PRICE_SCALE, RoundingMode.FLOOR));
            trade1.setPrice(new BigDecimal(c.getClose()));
            return trade1;
        }).collect(Collectors.toList());

        final BigDecimal averagePrice = walletService.getAveragePrice(trades);
        final BigDecimal quantity = walletService.getQuantity(trades);
        log.info("Valor total: {}", BigDecimal.valueOf(btcusdt.size()).multiply(quota));
        log.info("Quantidade total: {}", quantity);
        log.info("Preço médio: {}", averagePrice);
        log.info("Quantidade operações: {}", btcusdt.size());
    }

}
