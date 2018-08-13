package sk.bsmk.keycloak_social.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenReceiver {

  private static final Logger log = LoggerFactory.getLogger(TokenReceiver.class);

  private final TokenValidator tokenValidator;

  @Autowired
  public TokenReceiver(TokenValidator tokenValidator) {
    this.tokenValidator = tokenValidator;
  }

  @PostMapping("google-sign-in")
  public void receiveGoogleToken(
    @RequestBody JsonNode request
  ) {
//    exchangeJwt(request);
    exchangeAccessToken(request, "google");
  }

  @PostMapping("facebook-sign-in")
  public void receiveFacebookToken(
    @RequestBody JsonNode request
  ) {
    exchangeAccessToken(request, "facebook");
  }

  private void exchangeAccessToken(JsonNode request, String issuer) {
    log.info("Received social provider: {}", request);

    final String accessTokenSocial = request.get("accessToken").textValue();

    tokenValidator.validate(issuer, accessTokenSocial);

  }

  private void exchangeJwt(JsonNode request) {
    log.info("Received request: {}", request);

    final String jwt = request.get("idToken").textValue();

    tokenValidator.validate(jwt);

  }

}
