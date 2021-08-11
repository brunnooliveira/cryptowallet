package bg.com.bgdo.cryptowallet.infra.binance;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SymbolResponse {
    private String symbol;
    private BigDecimal price;
}
