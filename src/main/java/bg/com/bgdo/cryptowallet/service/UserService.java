package bg.com.bgdo.cryptowallet.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.com.bgdo.cryptowallet.model.User;
import bg.com.bgdo.cryptowallet.repository.UserRepository;
import bg.com.bgdo.cryptowallet.controller.request.RegisterRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private PasswordEncoder bcryptEncoder;
	private UserRepository userRepository;

	public User newUser(RegisterRequest user) {
		User newUser = new User();
		newUser.setId(UUID.randomUUID().toString());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setActive(false);
		return userRepository.save(newUser);
	}
}
