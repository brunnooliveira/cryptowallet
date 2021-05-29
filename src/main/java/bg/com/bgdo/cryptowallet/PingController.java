package bg.com.bgdo.cryptowallet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
public class PingController {

  @GetMapping
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("pong");
  }

}
