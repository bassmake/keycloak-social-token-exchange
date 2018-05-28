package sk.bsmk.keycloak_social.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class WebSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

  @Bean
  KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Override
  protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
    return new NullAuthenticatedSessionStrategy();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    super.configure(http);
    http
      .authorizeRequests()
//      .antMatchers("/api/owner/**").hasRole("owner")
//      .antMatchers("/api/manager/**").hasRole("manager")
//      .antMatchers("/authorized-api/**").fullyAuthenticated()
      .anyRequest().permitAll()
      .and()
      .csrf().disable();
  }

}
