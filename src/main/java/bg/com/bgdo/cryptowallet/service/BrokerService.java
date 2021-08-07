package bg.com.bgdo.cryptowallet.service;

import java.math.BigDecimal;

public interface BrokerService {

    public BigDecimal getAssetLastPrice(String asset);

}
