package bg.com.bgdo.cryptowallet.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Configuration
public class AppConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }

  @Bean
  @Primary
  public ObjectMapper objectMapper() {

    JavaTimeModule javaTimeModule = new JavaTimeModule();
//    javaTimeModule.addDeserializer( LocalDateTime.class, new LocalDateTimeDeserializer( DateTimeFormatter.ISO_DATE_TIME ) );
//    javaTimeModule.addDeserializer( LocalTime.class, new LocalTimeDeserializer( DateTimeFormatter.ISO_TIME ) );
    javaTimeModule.addSerializer( LocalDate.class, new LocalDateSerializer( DateTimeFormatter.ISO_DATE ) );

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(javaTimeModule);
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    return mapper;

//    @SuppressWarnings( "deprecation" )
//    JSR310Module jsr310Module = new JSR310Module();
//
//    JavaTimeModule javaTimeModule = new JavaTimeModule();
//    javaTimeModule.addDeserializer( LocalDateTime.class, new LocalDateTimeDeserializer( DateTimeFormatter.ISO_DATE_TIME ) );
//    javaTimeModule.addDeserializer( LocalTime.class, new LocalTimeDeserializer( DateTimeFormatter.ISO_TIME ) );
//    javaTimeModule.addSerializer( LocalDate.class, new LocalDateSerializer( DateTimeFormatter.ISO_DATE ) );
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
//    objectMapper.configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
//    objectMapper.registerModules( jsr310Module, javaTimeModule );
//
//    return objectMapper;
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
