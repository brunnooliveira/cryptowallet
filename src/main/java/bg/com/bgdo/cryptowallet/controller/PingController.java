package bg.com.bgdo.cryptowallet.controller;

import bg.com.bgdo.cryptowallet.shared.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PingController {

  @GetMapping(path = Constants.API_URL_BASE_PUBLIC + "/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
  }

  @GetMapping(path = Constants.API_URL_BASE_PRIVATE + "/ping")
  public ResponseEntity<LocalDate> pingPrivate() {
    return ResponseEntity.ok(LocalDate.now());
  }

}
