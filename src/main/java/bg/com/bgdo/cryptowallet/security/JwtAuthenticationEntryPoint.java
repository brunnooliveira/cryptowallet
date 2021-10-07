package bg.com.bgdo.cryptowallet.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

//		Exception exception = (Exception) request.getAttribute("exception");
		response.getOutputStream().write(getMessageBytesFromException(authException));
	}

	private byte[] getMessageBytesFromException(Exception exception) throws JsonProcessingException {
		String message;

		if (exception.getCause() != null) {
			message = exception.getCause().toString() + " " + exception.getMessage();
		} else {
			message = exception.getMessage();
		}

		return new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
	}

}
