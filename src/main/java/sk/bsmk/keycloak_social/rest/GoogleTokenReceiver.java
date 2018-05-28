package sk.bsmk.keycloak_social.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleTokenReceiver {

  private static final Logger log = LoggerFactory.getLogger(GoogleTokenReceiver.class);

  @PostMapping("token")
  public void receiveToken(
    @RequestBody ReceiveTokenRequest request
  ) {

    log.info("Received {}", request.getToken());


  }

}
