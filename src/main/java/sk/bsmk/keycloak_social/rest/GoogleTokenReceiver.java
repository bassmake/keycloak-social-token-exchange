package sk.bsmk.keycloak_social.rest;

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
public class GoogleTokenReceiver {

  private static final Logger log = LoggerFactory.getLogger(GoogleTokenReceiver.class);

  private final RestTemplate restTemplate;

  @Autowired
  public GoogleTokenReceiver(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostMapping("token-sign-in")
  public void receiveToken(
    @RequestBody ReceiveTokenRequest request
  ) {

    log.info("Received {}", request);

    final MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
    data.add("client_id", "social");
    data.add("client_secret", "65b08d30-437d-4036-8626-08b0b2b35297");
    data.add("subject_token", request.getAccessToken());
    data.add("subject_issuer", "google");
//    data.add("audience", "target-client");
    data.add("grant_type", "urn:ietf:params:oauth:grant-type:token-exchange");
    data.add("subject_token_type", "urn:ietf:params:oauth:token-type:access_token");

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    final HttpEntity<?> entity = new HttpEntity<>(data, headers);

    final ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/auth/realms/keycloak-poc/protocol/openid-connect/token",
      entity, String.class);

    log.info("Received from keycloak: {}", response.getBody());

  }

}
