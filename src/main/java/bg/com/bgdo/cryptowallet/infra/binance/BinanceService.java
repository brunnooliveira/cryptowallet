package bg.com.bgdo.cryptowallet.infra.binance;

import bg.com.bgdo.cryptowallet.service.BrokerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BinanceService implements BrokerService {

    private static List<SymbolResponse> symbols;
    private static LocalDateTime lastUpdate;
    private final RestTemplate restTemplate;

    public BinanceService() {
        symbols = new ArrayList<>();
        this.restTemplate = new RestTemplate();
    }

    @Override
    public BigDecimal getAssetLastPrice(String asset) {
        updateSymbols();
        final List<SymbolResponse> symbolResponses = symbols.stream()
                .filter(symbolResponse -> symbolResponse.getSymbol().equals(asset))
                .collect(Collectors.toList());

        final SymbolResponse symbol = !symbolResponses.isEmpty() ? symbolResponses.get(0) : new SymbolResponse();
        return symbol.getPrice();
    }

    private void updateSymbols() {
        if(lastUpdate == null || lastUpdate.plus(1, ChronoUnit.MINUTES).isBefore(LocalDateTime.now())) {
            final SymbolResponse[] symbolResponses = restTemplate.getForEntity(URI.create("https://api.binance.com/api/v3/ticker/price"), SymbolResponse[].class).getBody();
            symbols = symbolResponses!= null ? Arrays.asList( symbolResponses) : Collections.emptyList();
            lastUpdate = LocalDateTime.now();
        }
    }
}
