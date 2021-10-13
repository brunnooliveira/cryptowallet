package bg.com.bgdo.cryptowallet.service;

import bg.com.bgdo.cryptowallet.controller.request.RegisterRequest;
import bg.com.bgdo.cryptowallet.model.User;
import bg.com.bgdo.cryptowallet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

	private PasswordEncoder bcryptEncoder;
	private UserRepository userRepository;

	public User newUser(RegisterRequest user) {
		User newUser = new User();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setActive(false);
		userRepository.save(newUser);
		return newUser;
	}
}
