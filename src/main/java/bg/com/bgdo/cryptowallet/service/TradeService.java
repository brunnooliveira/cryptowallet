package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.repository.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TradeService {

	private TradeRepository tradeRepository;

	public Trade save(Trade trade) {

		if(trade.getId() == null || trade.getId().isEmpty()) {
			trade.setId(UUID.randomUUID().toString());
		}

		return tradeRepository.save(trade);
	}
}
