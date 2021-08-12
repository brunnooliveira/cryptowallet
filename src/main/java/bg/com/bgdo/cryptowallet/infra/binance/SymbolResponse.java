package bg.com.bgdo.cryptowallet.infra.binance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymbolResponse {
    private String symbol;
    private BigDecimal price;
}
