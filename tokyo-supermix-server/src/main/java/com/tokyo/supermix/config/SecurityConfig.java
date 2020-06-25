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
        //plant
        .antMatchers(HttpMethod.GET,EndpointURI.PLANTS).hasAnyAuthority(PermissionConstants.VIEW_PLANT)
        .antMatchers(HttpMethod.POST,EndpointURI.PLANT).hasAnyAuthority(PermissionConstants.CREATE_PLANT)
        .antMatchers(HttpMethod.PUT,EndpointURI.PLANT_BY_CODE).hasAnyAuthority(PermissionConstants.EDIT_PLANT)
        .antMatchers(HttpMethod.DELETE,EndpointURI.PLANT_BY_CODE).hasAnyAuthority(PermissionConstants.DELETE_PLANT)
        //congrete mixer
        .antMatchers(HttpMethod.GET,EndpointURI.CONCRETE_MIXERS).hasAnyAuthority(PermissionConstants.VIEW_CONCRETE_MIXER)
        .antMatchers(HttpMethod.POST,EndpointURI.CONCRETE_MIXER).hasAnyAuthority(PermissionConstants.CREATE_CONCRETE_MIXER)
        .antMatchers(HttpMethod.PUT,EndpointURI.CONCRETE_MIXER).hasAnyAuthority(PermissionConstants.EDIT_CONCRETE_MIXER)
        .antMatchers(HttpMethod.DELETE,EndpointURI.CONCRETE_MIXER_BY_ID).hasAnyAuthority(PermissionConstants.DELETE_CONCRETE_MIXER)
      //designation
        .antMatchers(HttpMethod.GET,EndpointURI.DESIGNATIONS).hasAnyAuthority(PermissionConstants.VIEW_DESIGNATION)
        .antMatchers(HttpMethod.POST,EndpointURI.DESIGNATION).hasAnyAuthority(PermissionConstants.CREATE_DESIGNATION)
        .antMatchers(HttpMethod.PUT,EndpointURI.DESIGNATION).hasAnyAuthority(PermissionConstants.EDIT_DESIGNATION)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DESIGNATION_BY_ID).hasAnyAuthority(PermissionConstants.DELETE_DESIGNATION)
      //employee
        .antMatchers(HttpMethod.GET,EndpointURI.EMPLOYEES).hasAnyAuthority(PermissionConstants.VIEW_EMPLOYEE)
        .antMatchers(HttpMethod.POST,EndpointURI.EMPLOYEE).hasAnyAuthority(PermissionConstants.CREATE_EMPLOYEE)
        .antMatchers(HttpMethod.PUT,EndpointURI.EMPLOYEE).hasAnyAuthority(PermissionConstants.EDIT_EMPLOYEE)
        .antMatchers(HttpMethod.DELETE,EndpointURI.EMPLOYEE_BY_ID).hasAnyAuthority(PermissionConstants.DELETE_EMPLOYEE)
        .anyRequest().authenticated();
    // Add our custom JWT security filter
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
