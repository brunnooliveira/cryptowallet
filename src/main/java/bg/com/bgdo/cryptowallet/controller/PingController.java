package bg.com.bgdo.cryptowallet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.com.bgdo.cryptowallet.shared.Constants;

@RestController
@RequestMapping(Constants.API_URL_BASE_PUBLIC + "/ping")
public class PingController {

  @GetMapping
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
  }

}
