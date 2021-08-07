package bg.com.bgdo.cryptowallet.service;

import java.math.BigDecimal;

public class BinanceService implements BrokerService {
    @Override
    public BigDecimal getAssetLastPrice(String asset) {
        return BigDecimal.valueOf(10.00);
    }
}
