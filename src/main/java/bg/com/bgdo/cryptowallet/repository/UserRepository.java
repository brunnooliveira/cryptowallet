package bg.com.bgdo.cryptowallet.repository;

import bg.com.bgdo.cryptowallet.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

}