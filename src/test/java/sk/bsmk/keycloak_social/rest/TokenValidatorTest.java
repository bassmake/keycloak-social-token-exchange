package sk.bsmk.keycloak_social.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sk.bsmk.keycloak_social.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TokenValidatorTest {

  @Autowired
  TokenValidator tokenValidator;

  @Test
  public void validateToken() {


    tokenValidator.validate("google",  "abc");


  }

}
