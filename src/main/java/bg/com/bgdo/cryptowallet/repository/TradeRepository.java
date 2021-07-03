package bg.com.bgdo.cryptowallet.repository;

import bg.com.bgdo.cryptowallet.model.Trade;
import bg.com.bgdo.cryptowallet.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends MongoRepository<Trade, String> {

}