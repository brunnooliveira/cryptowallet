package bg.com.bgdo.cryptowallet;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class CryptowalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptowalletApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDesciption, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
			.components(new Components()
				.addSecuritySchemes("bearer",
					new SecurityScheme()
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat("JWT")))
			.info(new Info()
				.title("CryptoWallet")
				.version(appVersion)
				.description(appDesciption))
			.addSecurityItem(
				new SecurityRequirement().addList("bearer", Arrays.asList("read", "write")));
	}

}
