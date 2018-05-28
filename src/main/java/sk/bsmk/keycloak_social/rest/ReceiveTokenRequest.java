package sk.bsmk.keycloak_social.rest;

public class ReceiveTokenRequest {

  private String idToken;

  private String accessToken;

  public String getIdToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @Override
  public String toString() {
    return "ReceiveTokenRequest{" +
      "idToken='" + idToken + '\'' +
      ", accessToken='" + accessToken + '\'' +
      '}';
  }
}
