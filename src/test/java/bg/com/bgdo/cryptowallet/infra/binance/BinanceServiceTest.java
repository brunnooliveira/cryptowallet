package bg.com.bgdo.cryptowallet.infra.binance;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.assertThat;

class BinanceServiceTest {

    @Test
    public void shouldReturnAssetLastPriceFromBinance(){
        final RestTemplate restTemplate = new RestTemplate();
        final BinanceService binanceService = new BinanceService(restTemplate);
        final BigDecimal assetLastPrice = binanceService.getAssetLastPrice("BTCUSDT");
        assertThat(assetLastPrice).isNotNull();
    }

}