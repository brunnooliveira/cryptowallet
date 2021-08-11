package bg.com.bgdo.cryptowallet.service.binance;

import bg.com.bgdo.cryptowallet.service.BrokerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BinanceService implements BrokerService {

    private static List<SymbolResponse> symbols;
    private static LocalDateTime lastUpdate;
    private RestTemplate restTemplate;

    @Override
    public BigDecimal getAssetLastPrice(String asset) {
        updateSymbols();
        final SymbolResponse symbol = symbols.stream()
                .filter(symbolResponse -> symbolResponse.getSymbol().equals(asset))
                .collect(Collectors.toList())
                .get(0);
        return symbol.getPrice();
    }

    private void updateSymbols() {
        if(lastUpdate == null || lastUpdate.plus(1, ChronoUnit.MINUTES).isBefore(LocalDateTime.now())) {
            final SymbolResponse[] symbolResponses = restTemplate.getForEntity(URI.create("https://api.binance.com/api/v3/ticker/price"), SymbolResponse[].class).getBody();
            symbols = Arrays.asList(symbolResponses);
            lastUpdate = LocalDateTime.now();
        }
    }

    public static LocalDateTime getLastUpdate(){
        return lastUpdate;
    }
}
