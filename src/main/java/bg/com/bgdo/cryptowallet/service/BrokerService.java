package bg.com.bgdo.cryptowallet.service;

import java.math.BigDecimal;

public interface BrokerService {
    BigDecimal getAssetLastPrice(String ticker);
}
