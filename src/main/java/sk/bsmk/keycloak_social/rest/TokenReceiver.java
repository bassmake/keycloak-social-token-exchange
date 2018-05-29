package sk.bsmk.keycloak_social.rest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TokenReceiver {

  private static final Logger log = LoggerFactory.getLogger(TokenReceiver.class);

  private final RestTemplate restTemplate;

  @Autowired
  public TokenReceiver(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("google-sign-in")
  public void receiveGoogleToken(
    @RequestBody JsonNode request
  ) {
    obtainToken(request, "google");
  }

  @PostMapping("facebook-sign-in")
  public void receiveFacebookToken(
    @RequestBody JsonNode request
  ) {
    obtainToken(request, "facebook");
  }

  private void obtainToken(JsonNode request, String issuer) {
    log.info("Received {}", request);

    final MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    data.add("client_id", "social");
    data.add("client_secret", "65b08d30-437d-4036-8626-08b0b2b35297");
    data.add("subject_token", request.get("accessToken").textValue());
    data.add("subject_issuer", issuer);
//    data.add("audience", "target-client");
    data.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
    data.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    final HttpEntity<?> entity = new HttpEntity<>(data, headers);

    final ResponseEntity<JsonNode> response = restTemplate.postForEntity("http://localhost:8080/auth/realms/keycloak-poc/protocol/openid-connect/token",
      entity, JsonNode.class);

    final String accessToken = response.getBody().get("access_token").asText();

    final DecodedJWT decoded = JWT.decode(accessToken);

    log.info("Received from keycloak: {}", accessToken);
    log.info("Received foo claim: {}", decoded.getClaims().get("foo").asString());
  }

}
