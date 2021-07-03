package bg.com.bgdo.cryptowallet.controller;

import bg.com.bgdo.cryptowallet.controller.request.AuthenticationRequest;
import bg.com.bgdo.cryptowallet.controller.request.AuthenticationResponse;
import bg.com.bgdo.cryptowallet.controller.request.RegisterRequest;
import bg.com.bgdo.cryptowallet.security.CustomUserDetailsService;
import bg.com.bgdo.cryptowallet.security.JwtUtil;
import bg.com.bgdo.cryptowallet.service.UserService;
import bg.com.bgdo.cryptowallet.shared.Constants;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = Constants.API_URL_BASE_PUBLIC)
public class AuthenticationController {

	private AuthenticationManager authenticationManager;
	private CustomUserDetailsService userDetailsService;
	private UserService userService;
	private JwtUtil jwtUtil;

	@PostMapping(path = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		UserDetails userdetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtUtil.generateToken(userdetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@PostMapping(path = "/register")
	public ResponseEntity<?> saveUser(@RequestBody RegisterRequest user) {
		userService.newUser(user);
		return ResponseEntity.ok().build();
	}
}
