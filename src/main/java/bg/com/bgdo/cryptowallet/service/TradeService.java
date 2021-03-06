package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.repository.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TradeService {

	private TradeRepository tradeRepository;

	public Trade save(Trade trade) {
		Instant now = Instant.now();
		if(trade.getId() == null || trade.getId().isEmpty()) {
			trade.setId(UUID.randomUUID().toString());
			trade.setCreatedAt(now);
		} else {
			Optional<Trade> savedTrade = tradeRepository.findById(trade.getId());
			savedTrade.orElseThrow(() -> new NoSuchElementException(String.format("Trade with id: {} not found", trade.getId())));
			trade.setCreatedAt(savedTrade.get().getCreatedAt());
		}
		trade.setUpdatedAt(now);
		tradeRepository.save(trade);
		return trade;
	}

	public Optional<Trade> findById(String id){
		return tradeRepository.findById(id);
	}

	public List<Trade> findAll(Trade trade){
		Trade tradeFilter = trade != null ? trade : new Trade();
		return tradeRepository.findAll(Example.of(tradeFilter), Sort.by(Sort.Direction.DESC, "date"));
	}

	public Page<Trade> findAllPageable(Pageable pageable, Trade trade){
		return tradeRepository.findAll(Example.of(trade), pageable);
	}
}

