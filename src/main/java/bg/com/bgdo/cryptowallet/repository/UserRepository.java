package bg.com.bgdo.cryptowallet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import bg.com.bgdo.cryptowallet.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	User findByUsername(String username);

}