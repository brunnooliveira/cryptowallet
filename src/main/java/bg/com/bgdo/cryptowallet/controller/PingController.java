package bg.com.bgdo.cryptowallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.com.bgdo.cryptowallet.shared.Constants;

@RestController
public class PingController {

  @GetMapping(path = Constants.API_URL_BASE_PUBLIC + "/ping")
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
  }

  @GetMapping(path = Constants.API_URL_BASE_PRIVATE + "/ping")
  public ResponseEntity<String> pingPrivate() {
    return ResponseEntity.ok("pong");
  }

}
