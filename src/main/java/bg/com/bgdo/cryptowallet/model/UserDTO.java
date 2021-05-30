package bg.com.bgdo.cryptowallet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String username;
	private String password;
	private String role;
}
