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
        .antMatchers("/api/v1/auth/**").permitAll().antMatchers("/swagger-ui.html").permitAll()
        .antMatchers(HttpMethod.GET, EndpointURI.PLANTS).hasAnyAuthority(PermissionConstants.VIEW_PLANT)
        // material category
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_CATEGORIES).hasAnyAuthority(PermissionConstants.VIEW_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_CATEGORY).hasAnyAuthority(PermissionConstants.CREATE_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_CATEGORY).hasAnyAuthority(PermissionConstants.EDIT_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_MATERIAL_CATEGORY).hasAnyAuthority(PermissionConstants.DELETE_MATERIAL_CATEGORY)
        // material state
        .antMatchers(HttpMethod.GET,EndpointURI.MATERIAL_STATES).hasAnyAuthority(PermissionConstants.VIEW_MATERIAL_STATE)
        .antMatchers(HttpMethod.POST,EndpointURI.MATERIAL_STATE).hasAnyAuthority(PermissionConstants.CREATE_MATERIAL_STATE)
        .antMatchers(HttpMethod.PUT,EndpointURI.MATERIAL_STATE).hasAnyAuthority(PermissionConstants.EDIT_MATERIAL_STATE)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_MATERIAL_STATE).hasAnyAuthority(PermissionConstants.DELETE_MATERIAL_STATE)
        // material sub category
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_SUB_CATEGORIES).hasAnyAuthority(PermissionConstants.VIEW_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_SUB_CATEGORY).hasAnyAuthority(PermissionConstants.CREATE_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_SUB_CATEGORY).hasAnyAuthority(PermissionConstants.EDIT_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_MATERIAL_SUB_CATEGORY).hasAnyAuthority(PermissionConstants.DELETE_MATERIAL_SUB_CATEGORY)   
        // material raw materials
        .antMatchers(HttpMethod.GET,EndpointURI.RAW_MATERIALS).hasAnyAuthority(PermissionConstants.VIEW_RAW_MATERIAL)
        .antMatchers(HttpMethod.POST,EndpointURI.RAW_MATERIAL).hasAnyAuthority(PermissionConstants.CREATE_RAW_MATERIAL)
        .antMatchers(HttpMethod.PUT,EndpointURI.RAW_MATERIAL).hasAnyAuthority(PermissionConstants.EDIT_RAW_MATERIAL)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_RAW_MATERIAL).hasAnyAuthority(PermissionConstants.DELETE_RAW_MATERIAL)
        // material equipment
        .antMatchers(HttpMethod.GET,EndpointURI.EQUIPMENTS).hasAnyAuthority(PermissionConstants.VIEW_EQUIPMENT)
        .antMatchers(HttpMethod.POST,EndpointURI.EQUIPMENT).hasAnyAuthority(PermissionConstants.CREATE_EQUIPMENT)
        .antMatchers(HttpMethod.PUT,EndpointURI.EQUIPMENT).hasAnyAuthority(PermissionConstants.EDIT_EQUIPMENT)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_EQUIPMENT).hasAnyAuthority(PermissionConstants.DELETE_EQUIPMENT)
        // material equipment plant
        .antMatchers(HttpMethod.GET,EndpointURI.PLANT_EQUIPMENTS).hasAnyAuthority(PermissionConstants.VIEW_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.POST,EndpointURI.PLANT_EQUIPMENT).hasAnyAuthority(PermissionConstants.CREATE_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.PUT,EndpointURI.PLANT_EQUIPMENT).hasAnyAuthority(PermissionConstants.EDIT_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_PLANT_EQUIPMENT).hasAnyAuthority(PermissionConstants.DELETE_PLANT_EQUIPMENT)
        // material equipment plant
        .antMatchers(HttpMethod.GET,EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS).hasAnyAuthority(PermissionConstants.VIEW_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.POST,EndpointURI.EQUIPMENT_PLANT_CALIBRATION).hasAnyAuthority(PermissionConstants.CREATE_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.PUT,EndpointURI.EQUIPMENT_PLANT_CALIBRATION).hasAnyAuthority(PermissionConstants.EDIT_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.DELETE,EndpointURI.DELETE_EQUIPMENT_PLANT_CALIBRATION).hasAnyAuthority(PermissionConstants.DELETE_PLANT_EQUIPMENT_CALIBRATION)
        // supplier category
        .antMatchers(HttpMethod.GET, EndpointURI.SUPPLIER_CATEGORIES).hasAnyAuthority(PermissionConstants.VIEW_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.SUPPLIER_CATEGORY).hasAnyAuthority(PermissionConstants.CREATE_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.SUPPLIER_CATEGORY).hasAnyAuthority(PermissionConstants.EDIT_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_SUPPLIER_CATEGORY).hasAnyAuthority(PermissionConstants.DELETE_SUPPLIER_CATEGORY)   
        // unit
        .antMatchers(HttpMethod.GET, EndpointURI.UNITS).hasAnyAuthority(PermissionConstants.VIEW_UNIT)
        .antMatchers(HttpMethod.POST, EndpointURI.UNIT).hasAnyAuthority(PermissionConstants.CREATE_UNIT)
        .antMatchers(HttpMethod.PUT, EndpointURI.UNIT).hasAnyAuthority(PermissionConstants.EDIT_UNIT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_UNIT_BY_ID).hasAnyAuthority(PermissionConstants.DELETE_UNIT) 
        // customer
        .antMatchers(HttpMethod.GET, EndpointURI.CUSTOMERS).hasAnyAuthority(PermissionConstants.VIEW_CUSTOMER)
        .antMatchers(HttpMethod.POST, EndpointURI.CUSTOMER).hasAnyAuthority(PermissionConstants.CREATE_CUSTOMER)
        .antMatchers(HttpMethod.PUT, EndpointURI.CUSTOMER).hasAnyAuthority(PermissionConstants.EDIT_CUSTOMER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_CUSTOMER).hasAnyAuthority(PermissionConstants.DELETE_CUSTOMER); 
    // .anyRequest().authenticated();
    // Add our custom JWT security filter
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
