package bg.com.bgdo.cryptowallet.service.binance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SymbolResponse {
    private String symbol;
    private BigDecimal price;
}
