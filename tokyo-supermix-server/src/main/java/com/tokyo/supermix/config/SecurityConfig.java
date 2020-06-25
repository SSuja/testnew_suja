package com.tokyo.supermix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.tokyo.supermix.EndpointURI;
import com.tokyo.supermix.security.JwtAuthenticationEntryPoint;
import com.tokyo.supermix.security.JwtAuthenticationFilter;
import com.tokyo.supermix.server.services.auth.AuthUserDetailsService;
import com.tokyo.supermix.util.privilege.PermissionConstants;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  AuthUserDetailsService authUserDetailsService;
  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
      throws Exception {
    authenticationManagerBuilder.userDetailsService(authUserDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers("/api/v1/auth/**").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers(HttpMethod.GET,EndpointURI.PLANTS).hasAnyAuthority(PermissionConstants.VIEW_PLANT)
        .antMatchers(HttpMethod.GET,EndpointURI.MATERIAL_STATES).hasAnyAuthority(PermissionConstants.VIEW_MATERIAL_STATE)
        .antMatchers(HttpMethod.POST,EndpointURI.MATERIAL_STATE).hasAnyAuthority(PermissionConstants.CREATE_MATERIAL_STATE)
        .antMatchers(HttpMethod.PUT,EndpointURI.MATERIAL_STATE).hasAnyAuthority(PermissionConstants.EDIT_MATERIAL_STATE)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_MATERIAL_STATE).hasAnyAuthority(PermissionConstants.DELETE_MATERIAL_STATE)
        ;
    
    
    // .anyRequest().authenticated();
    // Add our custom JWT security filter
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
