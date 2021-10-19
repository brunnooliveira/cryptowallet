package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Asset;
import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.shared.Constants;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;
    @Mock
    private TradeService tradeService;
    @Mock
    private BrokerService brokerService;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnWalletAssetQuantityAndAveragePrice() {
        when(tradeService.findAll(null)).thenReturn(getTrades());
        when(brokerService.getAssetLastPrice(any())).thenReturn(BigDecimal.TEN);
        final List<Asset> wallet = walletService.getAssets(null);

        assertThat(wallet.size()).isEqualTo(1);
        assertThat(wallet.get(0).getAmount()).isEqualTo(BigDecimal.valueOf(2l));
        assertThat(wallet.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(15l).setScale(Constants.PRICE_SCALE));
    }

    @Test
    void shouldReturnWalletAveragePriceOfAsset() {
        final BigDecimal averagePrice = walletService.getAveragePrice(getTrades());
        assertEquals(new BigDecimal(15.0).setScale(Constants.PRICE_SCALE), averagePrice);
    }

    @Test
    void shouldReturnWalletQuantityOfAsset() {
        final BigDecimal quantity = walletService.getQuantity(getTrades());
        assertEquals(new BigDecimal(2.0), quantity);
    }

    private List<Trade> getTrades(){
        Trade trade1 = new Trade();
        trade1.setTicker("BTCUSD");
        trade1.setAmount(new BigDecimal(1.0));
        trade1.setPrice(new BigDecimal(10.0));

        Trade trade2 = new Trade();
        trade2.setTicker("BTCUSD");
        trade2.setAmount(new BigDecimal(1.0));
        trade2.setPrice(new BigDecimal(20.0));

        return Arrays.asList(trade1, trade2);
    }
}