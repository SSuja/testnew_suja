package com.gateway.zuul.security;

import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.netflix.zuul.ZuulFilter;

@EnableAutoConfiguration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Bean
  public JwtTokenAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtTokenAuthenticationFilter();
  }
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().exceptionHandling()
        .authenticationEntryPoint(
            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers("/auth-service/**").permitAll()
        .anyRequest().authenticated();
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
