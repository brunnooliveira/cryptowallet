package bg.com.bgdo.cryptowallet.infra.binance;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.BDDAssertions.assertThat;

class BinanceServiceTest {

    @Test
    public void shouldReturnAssetLastPriceFromBinance(){
        final BinanceService binanceService = new BinanceService();
        final BigDecimal assetLastPrice = binanceService.getAssetLastPrice("BTCUSDT");
        assertThat(assetLastPrice).isNotNull();
    }

}