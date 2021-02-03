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
import com.tokyo.supermix.PrivilegeEndpointURI;
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
        .antMatchers("/api/v1/auth/**").permitAll().antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/downloadFile/**").permitAll()
        .antMatchers(HttpMethod.GET,"/api/v1/employee/confirmation/**").permitAll() 
        .antMatchers(HttpMethod.GET,"/api/v1/mix-design/confirmation/**").permitAll() 
        // plant
        .antMatchers(HttpMethod.GET, EndpointURI.PLANTS)
        .hasAuthority(PermissionConstants.VIEW_PLANT)
        .antMatchers(HttpMethod.POST, EndpointURI.PLANT)
        .hasAuthority(PermissionConstants.CREATE_PLANT)
        .antMatchers(HttpMethod.PUT, EndpointURI.PLANT).hasAuthority(PermissionConstants.EDIT_PLANT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PLANT_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_PLANT)
        // designation
        .antMatchers(HttpMethod.GET, EndpointURI.DESIGNATIONS)
        .hasAuthority(PermissionConstants.VIEW_DESIGNATION)
        .antMatchers(HttpMethod.POST, EndpointURI.DESIGNATION)
        .hasAuthority(PermissionConstants.CREATE_DESIGNATION)
        .antMatchers(HttpMethod.PUT, EndpointURI.DESIGNATION)
        .hasAuthority(PermissionConstants.EDIT_DESIGNATION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DESIGNATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_DESIGNATION)
        // employee
        .antMatchers(HttpMethod.GET, EndpointURI.EMPLOYEE_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_EMPLOYEE)
        .antMatchers(HttpMethod.POST, EndpointURI.EMPLOYEE)
        .hasAuthority(PermissionConstants.CREATE_EMPLOYEE)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMPLOYEE)
        .hasAuthority(PermissionConstants.EDIT_EMPLOYEE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EMPLOYEE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_EMPLOYEE)
        // material category
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_CATEGORIES)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_CATEGORY)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_CATEGORY)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_CATEGORY_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_CATEGORY)
        // material state
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_STATES)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_STATE)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_STATE)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_STATE)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_STATE)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_STATE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_STATE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_STATE)
        // material sub category
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_SUB_CATEGORIES)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_SUB_CATEGORY)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_SUB_CATEGORY)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_SUB_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_SUB_CATEGORY_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_SUB_CATEGORY)
        // material raw materials
        .antMatchers(HttpMethod.GET, EndpointURI.RAW_MATERIALS)
        .hasAuthority(PermissionConstants.VIEW_RAW_MATERIAL)
        .antMatchers(HttpMethod.POST, EndpointURI.RAW_MATERIAL)
        .hasAuthority(PermissionConstants.CREATE_RAW_MATERIAL)
        .antMatchers(HttpMethod.PUT, EndpointURI.RAW_MATERIAL)
        .hasAuthority(PermissionConstants.EDIT_RAW_MATERIAL)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_RAW_MATERIAL)
        .hasAuthority(PermissionConstants.DELETE_RAW_MATERIAL)
        // material equipment
        .antMatchers(HttpMethod.GET, EndpointURI.EQUIPMENTS)
        .hasAuthority(PermissionConstants.VIEW_EQUIPMENT)
        .antMatchers(HttpMethod.POST, EndpointURI.EQUIPMENT)
        .hasAuthority(PermissionConstants.CREATE_EQUIPMENT)
        .antMatchers(HttpMethod.PUT, EndpointURI.EQUIPMENT)
        .hasAuthority(PermissionConstants.EDIT_EQUIPMENT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.DELETE_EQUIPMENT)
        .hasAuthority(PermissionConstants.DELETE_EQUIPMENT)
        // material equipment plant
        .antMatchers(HttpMethod.GET, EndpointURI.PLANT_EQUIPMENTS_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.POST, EndpointURI.PLANT_EQUIPMENT)
        .hasAuthority(PermissionConstants.CREATE_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.PUT, EndpointURI.PLANT_EQUIPMENT)
        .hasAuthority(PermissionConstants.EDIT_PLANT_EQUIPMENT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PLANTEQUIPMENT_BY_SERIALNO)
        .hasAuthority(PermissionConstants.DELETE_PLANT_EQUIPMENT)
        // material equipment plant calibrarion
        .antMatchers(HttpMethod.GET, EndpointURI.EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.POST, EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
        .hasAuthority(PermissionConstants.CREATE_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.PUT, EndpointURI.EQUIPMENT_PLANT_CALIBRATION)
        .hasAuthority(PermissionConstants.EDIT_PLANT_EQUIPMENT_CALIBRATION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EQUIPMENT_PLANT_CALIBRATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_PLANT_EQUIPMENT_CALIBRATION)
        // supplier category
        .antMatchers(HttpMethod.GET, EndpointURI.SUPPLIER_CATEGORIES)
        .hasAuthority(PermissionConstants.VIEW_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.POST, EndpointURI.SUPPLIER_CATEGORY)
        .hasAuthority(PermissionConstants.CREATE_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.PUT, EndpointURI.SUPPLIER_CATEGORY)
        .hasAuthority(PermissionConstants.EDIT_SUPPLIER_CATEGORY)
        .antMatchers(HttpMethod.DELETE, EndpointURI.SUPPLIER_CATEGORY_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_SUPPLIER_CATEGORY)
        // project
        .antMatchers(HttpMethod.GET, EndpointURI.PROJECT_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_PROJECT)
        .antMatchers(HttpMethod.POST, EndpointURI.PROJECT)
        .hasAuthority(PermissionConstants.CREATE_PROJECT)
        .antMatchers(HttpMethod.PUT, EndpointURI.PROJECT)
        .hasAuthority(PermissionConstants.EDIT_PROJECT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PROJECT_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_PROJECT)
        // pour
        .antMatchers(HttpMethod.GET, EndpointURI.POUR_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_POUR).antMatchers(HttpMethod.POST, EndpointURI.POUR)
        .hasAuthority(PermissionConstants.CREATE_POUR).antMatchers(HttpMethod.PUT, EndpointURI.POUR)
        .hasAuthority(PermissionConstants.EDIT_POUR)
        .antMatchers(HttpMethod.DELETE, EndpointURI.POUR_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_POUR)
        // unit
        .antMatchers(HttpMethod.GET, EndpointURI.UNITS).hasAuthority(PermissionConstants.VIEW_UNIT)
        .antMatchers(HttpMethod.POST, EndpointURI.UNIT)
        .hasAuthority(PermissionConstants.CREATE_UNIT).antMatchers(HttpMethod.PUT, EndpointURI.UNIT)
        .hasAuthority(PermissionConstants.EDIT_UNIT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.UNIT_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_UNIT)
        // customer
        .antMatchers(HttpMethod.GET, EndpointURI.CUSTOMERS)
        .hasAuthority(PermissionConstants.VIEW_CUSTOMER)
        .antMatchers(HttpMethod.POST, EndpointURI.CUSTOMER)
        .hasAuthority(PermissionConstants.CREATE_CUSTOMER)
        .antMatchers(HttpMethod.PUT, EndpointURI.CUSTOMER)
        .hasAuthority(PermissionConstants.EDIT_CUSTOMER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.CUSTOMER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_CUSTOMER)
        // supplier
        .antMatchers(HttpMethod.GET, EndpointURI.SUPPLIER_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_SUPPLIER)
        .antMatchers(HttpMethod.POST, EndpointURI.SUPPLIER)
        .hasAuthority(PermissionConstants.CREATE_SUPPLIER)
        .antMatchers(HttpMethod.PUT, EndpointURI.SUPPLIER)
        .hasAuthority(PermissionConstants.EDIT_SUPPLIER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.SUPPLIER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_SUPPLIER)
        // mix design
        .antMatchers(HttpMethod.GET, EndpointURI.MIX_DESIGN_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_MIX_DESIGN)
        .antMatchers(HttpMethod.POST, EndpointURI.MIX_DESIGN)
        .hasAuthority(PermissionConstants.CREATE_MIX_DESIGN)
        .antMatchers(HttpMethod.PUT, EndpointURI.MIX_DESIGN)
        .hasAuthority(PermissionConstants.EDIT_MIX_DESIGN)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MIX_DESIGN_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_MIX_DESIGN)
        // Incoming sample
        .antMatchers(HttpMethod.GET, EndpointURI.INCOMING_SAMPLE_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_INCOMING_SAMPLE)
        .antMatchers(HttpMethod.POST, EndpointURI.INCOMING_SAMPLE)
        .hasAuthority(PermissionConstants.CREATE_INCOMING_SAMPLE)
        .antMatchers(HttpMethod.PUT, EndpointURI.INCOMING_SAMPLE)
        .hasAuthority(PermissionConstants.EDIT_INCOMING_SAMPLE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.INCOMING_SAMPLE_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_INCOMING_SAMPLE)
        // finished product sample
        .antMatchers(HttpMethod.GET, EndpointURI.FINISH_PRODUCT_SAMPLE_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE)
        .antMatchers(HttpMethod.POST, EndpointURI.FINISH_PRODUCT_SAMPLE)
        .hasAuthority(PermissionConstants.CREATE_FINISH_PRODUCT_SAMPLE)
        .antMatchers(HttpMethod.PUT, EndpointURI.FINISH_PRODUCT_SAMPLE)
        .hasAuthority(PermissionConstants.EDIT_FINISH_PRODUCT_SAMPLE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.FINISH_PRODUCT_SAMPLE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_FINISH_PRODUCT_SAMPLE)
        // Test
        .antMatchers(HttpMethod.GET, EndpointURI.TESTS).hasAuthority(PermissionConstants.VIEW_TEST)
        .antMatchers(HttpMethod.POST, EndpointURI.TEST)
        .hasAuthority(PermissionConstants.CREATE_TEST).antMatchers(HttpMethod.PUT, EndpointURI.TEST)
        .hasAuthority(PermissionConstants.EDIT_TEST)
        .antMatchers(HttpMethod.DELETE, EndpointURI.TEST_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_TEST)
        // Parameter
        .antMatchers(HttpMethod.GET, EndpointURI.PARAMETERS)
        .hasAuthority(PermissionConstants.VIEW_PARAMETER)
        .antMatchers(HttpMethod.POST, EndpointURI.PARAMETER)
        .hasAuthority(PermissionConstants.CREATE_PARAMETER)
        .antMatchers(HttpMethod.PUT, EndpointURI.PARAMETER)
        .hasAuthority(PermissionConstants.EDIT_PARAMETER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PARAMETER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_PARAMETER)
        // equation
        .antMatchers(HttpMethod.GET, EndpointURI.EQUATIONS)
        .hasAuthority(PermissionConstants.VIEW_EQUATION)
        .antMatchers(HttpMethod.POST, EndpointURI.EQUATION)
        .hasAuthority(PermissionConstants.CREATE_EQUATION)
        .antMatchers(HttpMethod.PUT, EndpointURI.EQUATION)
        .hasAuthority(PermissionConstants.EDIT_EQUATION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EQUATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_EQUATION)
        // parameter result
        .antMatchers(HttpMethod.GET, EndpointURI.PARAMETER_RESULTS)
        .hasAuthority(PermissionConstants.VIEW_PARAMETER_RESULT)
        .antMatchers(HttpMethod.POST, EndpointURI.PARAMETER_RESULT)
        .hasAuthority(PermissionConstants.CREATE_PARAMETER_RESULT)
        .antMatchers(HttpMethod.PUT, EndpointURI.PARAMETER_RESULT)
        .hasAuthority(PermissionConstants.EDIT_PARAMETER_RESULT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PARAMETER_RESULT_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_PARAMETER_RESULT)
        // material accepted value
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_ACCEPTED_VALUES)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_ACCEPTED_VALUE)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_ACCEPTED_VALUE)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_ACCEPTED_VALUE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_ACCEPTED_VALUE)

        // Test
        .antMatchers(HttpMethod.GET, EndpointURI.TEST_CONFIGURES)
        .hasAuthority(PermissionConstants.VIEW_TEST_CONFIGURE)
        .antMatchers(HttpMethod.POST, EndpointURI.TEST_CONFIGURE)
        .hasAuthority(PermissionConstants.CREATE_TEST_CONFIGURE)
        .antMatchers(HttpMethod.PUT, EndpointURI.TEST_CONFIGURE)
        .hasAuthority(PermissionConstants.EDIT_TEST_CONFIGURE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.TEST_CONFIGURE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_TEST_CONFIGURE)
        // Test Parameter
        .antMatchers(HttpMethod.GET, EndpointURI.TEST_PARAMETERS)
        .hasAuthority(PermissionConstants.VIEW_TEST_PARAMETER)
        .antMatchers(HttpMethod.POST, EndpointURI.TEST_PARAMETER)
        .hasAuthority(PermissionConstants.CREATE_TEST_PARAMETER)
        .antMatchers(HttpMethod.PUT, EndpointURI.TEST_PARAMETER)
        .hasAuthority(PermissionConstants.EDIT_TEST_PARAMETER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.TEST_PARAMETER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_TEST_PARAMETER)
        // Incoming sample count
         .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_SUB_CATEGORY)
         .hasAuthority(PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS)
         .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_SUB_CATEGORY_STATUS_COUNT)
         .hasAuthority(PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS)
         .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_SAMPLE_COUNT_BY_MATERIAL_CATEGORY)
         .hasAuthority(PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS)
         .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_CATEGORY_STATUS_COUNT)
         .hasAuthority(PermissionConstants.DASHBOARD_MATERIAL_COUNT_STATUS)
        // Parameter Equation
        .antMatchers(HttpMethod.GET, EndpointURI.PARAMETER_EQUATIONS)
        .hasAuthority(PermissionConstants.VIEW_PARAMETER_EQUATION)
        .antMatchers(HttpMethod.POST, EndpointURI.PARAMETER_EQUATION)
        .hasAuthority(PermissionConstants.CREATE_PARAMETER_EQUATION)
        .antMatchers(HttpMethod.PUT, EndpointURI.PARAMETER_EQUATION)
        .hasAuthority(PermissionConstants.EDIT_PARAMETER_EQUATION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PARAMETER_EQUATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_PARAMETER_EQUATION)
        // process-sample
        .antMatchers(HttpMethod.GET, EndpointURI.PROCESS_SAMPLE_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_PROCESS_SAMPLE)
        .antMatchers(HttpMethod.POST, EndpointURI.PROCESS_SAMPLE)
        .hasAuthority(PermissionConstants.CREATE_PROCESS_SAMPLE)
        .antMatchers(HttpMethod.PUT, EndpointURI.PROCESS_SAMPLE)
        .hasAuthority(PermissionConstants.EDIT_PROCESS_SAMPLE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.PROCESS_SAMPLE_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_PROCESS_SAMPLE)
        // mix design proportions
        .antMatchers(HttpMethod.GET, EndpointURI.MIX_DESIGN_PROPORTIONS)
        .hasAuthority(PermissionConstants.VIEW_MIX_DESIGN_PROPORTION)
        .antMatchers(HttpMethod.POST, EndpointURI.MIX_DESIGN_PROPORTION)
        .hasAuthority(PermissionConstants.CREATE_MIX_DESIGN_PROPORTION)
        .antMatchers(HttpMethod.PUT, EndpointURI.MIX_DESIGN_PROPORTION)
        .hasAuthority(PermissionConstants.EDIT_MIX_DESIGN_PROPORTION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MIX_DESIGN_PROPORTION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MIX_DESIGN_PROPORTION)
        // finish product
         .antMatchers(HttpMethod.GET, EndpointURI.FINISH_PRODUCT_TEST_BY_PLANT)
         .hasAuthority(PermissionConstants.VIEW_FINISH_PRODUCT_TEST)
         .antMatchers(HttpMethod.POST, EndpointURI.FINISH_PRODUCT_TEST)
         .hasAuthority(PermissionConstants.CREATE_FINISH_PRODUCT_TEST)
         .antMatchers(HttpMethod.PUT, EndpointURI.FINISH_PRODUCT_TEST)
         .hasAuthority(PermissionConstants.EDIT_FINISH_PRODUCT_TEST)
         .antMatchers(HttpMethod.DELETE, EndpointURI.FINISH_PRODUCT_TEST_BY_CODE)
         .hasAuthority(PermissionConstants.DELETE_FINISH_PRODUCT_TEST)
        // Accepted value
        .antMatchers(HttpMethod.GET, EndpointURI.ACCEPTED_VALUES)
        .hasAuthority(PermissionConstants.VIEW_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.POST, EndpointURI.ACCEPTED_VALUE)
        .hasAuthority(PermissionConstants.CREATE_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.PUT, EndpointURI.ACCEPTED_VALUE)
        .hasAuthority(PermissionConstants.EDIT_ACCEPTED_VALUE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.ACCEPTED_VALUE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_ACCEPTED_VALUE)
        // Material test
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_TESTS)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_TEST)
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_TEST_BY_PLANT)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_TEST)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_TEST)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_TEST)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_TESTS_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_TEST)
        // Material test trail
        .antMatchers(HttpMethod.POST, EndpointURI.MATERIAL_TEST_TRIAL)
        .hasAuthority(PermissionConstants.CREATE_MATERIAL_TEST_TRIAL)
        .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_TEST_TRIAL_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_MATERIAL_TEST_TRIAL)
        .antMatchers(HttpMethod.PUT, EndpointURI.MATERIAL_TEST_TRIAL)
        .hasAuthority(PermissionConstants.EDIT_MATERIAL_TEST_TRIAL)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MATERIAL_TEST_TRIAL_BY_CODE)
        .hasAuthority(PermissionConstants.DELETE_MATERIAL_TEST_TRIAL)

        // finish product sample issue
        .antMatchers(HttpMethod.POST, EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE)
        .hasAuthority(PermissionConstants.CREATE_FINISH_PRODUCT_SAMPLE_ISSUE)
        .antMatchers(HttpMethod.GET, EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT)
        .hasAuthority(PermissionConstants.VIEW_FINISH_PRODUCT_SAMPLE_ISSUE)
        .antMatchers(HttpMethod.PUT, EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE)
        .hasAuthority(PermissionConstants.EDIT_FINISH_PRODUCT_SAMPLE_ISSUE)
        .antMatchers(HttpMethod.DELETE, EndpointURI.FINISH_PRODUCT_SAMPLE_ISSUE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_FINISH_PRODUCT_SAMPLE_ISSUE)
        // finish product trail
         .antMatchers(HttpMethod.POST, EndpointURI.FINISH_PRODUCT_TRIAL)
         .hasAuthority(PermissionConstants.CREATE_FINISH_PRODUCT_TRAIL)
         .antMatchers(HttpMethod.GET, EndpointURI.FINISH_PRODUCT_TRIAL_BY_PLANT)
         .hasAuthority(PermissionConstants.VIEW_FINISH_PRODUCT_TRAIL)
         .antMatchers(HttpMethod.PUT, EndpointURI.FINISH_PRODUCT_TRIAL)
         .hasAuthority(PermissionConstants.EDIT_FINISH_PRODUCT_TRAIL)
         .antMatchers(HttpMethod.DELETE, EndpointURI.FINISH_PRODUCT_TRIAL_BY_ID)
         .hasAuthority(PermissionConstants.DELETE_FINISH_PRODUCT_TRAIL)
        // quality parameter
        .antMatchers(HttpMethod.POST, EndpointURI.QUALITY_PARAMETER)
        .hasAuthority(PermissionConstants.CREATE_QUALITY_PARAMETER)
        .antMatchers(HttpMethod.GET, EndpointURI.QUALITY_PARAMETERS)
        .hasAuthority(PermissionConstants.VIEW_QUALITY_PARAMETER)
        .antMatchers(HttpMethod.PUT, EndpointURI.QUALITY_PARAMETER)
        .hasAuthority(PermissionConstants.EDIT_QUALITY_PARAMETER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.QUALITY_PARAMETER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_QUALITY_PARAMETER)
        // file export
        .antMatchers(HttpMethod.POST, EndpointURI.UPLOAD_MIXDESIGN)
        .hasAuthority(PermissionConstants.CREATE_UPLOAD_MIXDESIGN)
        .antMatchers(HttpMethod.GET, EndpointURI.EXPORT_MIXDESIGN)
        .hasAuthority(PermissionConstants.VIEW_EXPORT_MIXDESIGN)
        // test Report
         .antMatchers(HttpMethod.GET, EndpointURI.MATERIAL_TEST_REPORT_DETAIL)
         .hasAuthority(PermissionConstants.MATERIAL_TEST_REPORT)
         .antMatchers(HttpMethod.GET, EndpointURI.INCOMING_SAMPLE_SUMMARY_REPORT)
         .hasAuthority(PermissionConstants.INCOMING_SAMPLE_SUMMARY_REPORT)
         .antMatchers(HttpMethod.GET, EndpointURI.INCOMING_SAMPLE_DELIVERY_REPORT)
         .hasAuthority(PermissionConstants.INCOMING_SAMPLE_DELIVERY_REPORT)

        // Role Permission
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.ROLE_PERMISSION)
        .hasAuthority(PermissionConstants.EDIT_ROLE_PERMISSION)
        // Plant Role Plant Permission
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.PLANT_ROLE_PLANT_PERMISSION)
        .hasAuthority(PermissionConstants.EDIT_PLANT_ROLE_PLANT_PERMISSION)
        // User Plant Permission
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.USER_PLANT_PERMISSION)
        .hasAuthority(PermissionConstants.EDIT_USER_PLANT_PERMISSION)
        // role
        .antMatchers(HttpMethod.GET, PrivilegeEndpointURI.ROLES)
        .hasAuthority(PermissionConstants.VIEW_ROLE)
        .antMatchers(HttpMethod.POST, PrivilegeEndpointURI.ROLE)
        .hasAuthority(PermissionConstants.CREATE_ROLE)
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.ROLE)
        .hasAuthority(PermissionConstants.EDIT_ROLE)
        .antMatchers(HttpMethod.DELETE, PrivilegeEndpointURI.ROLE_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_ROLE)
        // user
        .antMatchers(HttpMethod.GET, PrivilegeEndpointURI.USERS)
        .hasAuthority(PermissionConstants.VIEW_USER)
        .antMatchers(HttpMethod.POST, PrivilegeEndpointURI.USER)
        .hasAuthority(PermissionConstants.CREATE_USER)
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.UPDATE_USER_STATUS_BY_ID)
        .hasAuthority(PermissionConstants.EDIT_USER_STATUS)
        .antMatchers(HttpMethod.PUT, PrivilegeEndpointURI.UPDATE_USER_ROLE)
        .hasAuthority(PermissionConstants.EDIT_USER_ROLE)
        .antMatchers(HttpMethod.DELETE, PrivilegeEndpointURI.USER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_USER)

        // email points
        .antMatchers(HttpMethod.GET, EndpointURI.EMAIL_POINTS)
        .hasAuthority(PermissionConstants.GET_EMAIL_POINTS)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMAIL_POINT)
        .hasAuthority(PermissionConstants.EDIT_EMAIL_POINTS)

        // email notification
        .antMatchers(HttpMethod.GET, EndpointURI.EMAIL_GROUP_BY_ADMIN_STATUS)
        .hasAuthority(PermissionConstants.GET_EMAIL_GROUP)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMAIL_GROUP)
        .hasAuthority(PermissionConstants.UPDATE_EMAIL_GROUP)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMAIL_GROUP_EDIT_NAME)
        .hasAuthority(PermissionConstants.UPDATE_EMAIL_GROUP)
        .antMatchers(HttpMethod.POST, EndpointURI.EMAIL_GROUP)
        .hasAuthority(PermissionConstants.ADD_EMAIL_GROUP)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EMAIL_GROUP_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_EMAIL_GROUP)

        // email group configuration
        .antMatchers(HttpMethod.GET, EndpointURI.EMAIL_RECIPIENTS_BY_RECIPIENT_TYPE)
        .hasAuthority(PermissionConstants.GET_EMAIL_RECIPIENT)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMAIL_RECIPIENT)
        .hasAuthority(PermissionConstants.UPDATE_EMAIL_GROUP)
        .antMatchers(HttpMethod.POST, EndpointURI.EMAIL_RECIPIENT)
        .hasAuthority(PermissionConstants.ADD_EMAIL_RECIPIENT)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EMAIL_RECIPIENT_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_EMAIL_RECIPIENT)

        // notification days
        .antMatchers(HttpMethod.GET, EndpointURI.EMAIL_NOTIFICATIONS_BY_PLANT_CODE)
        .hasAuthority(PermissionConstants.GET_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.PUT, EndpointURI.EMAIL_NOTIFICATION)
        .hasAuthority(PermissionConstants.EDIT_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.POST, EndpointURI.EMAIL_NOTIFICATION)
        .hasAuthority(PermissionConstants.ADD_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.DELETE, EndpointURI.EMAIL_NOTIFICATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_NOTIFICATION_DAYS)

        // mac address 
        .antMatchers(HttpMethod.GET, EndpointURI.MAC_ADDRESSES)
        .hasAuthority(PermissionConstants.GET_MAC_ADDRESS)
        .antMatchers(HttpMethod.PUT, EndpointURI.MAC_ADDRESS)
        .hasAuthority(PermissionConstants.EDIT_MAC_ADDRESS)
        .antMatchers(HttpMethod.POST, EndpointURI.MAC_ADDRESS)
        .hasAuthority(PermissionConstants.ADD_MAC_ADDRESS)
        .antMatchers(HttpMethod.DELETE, EndpointURI.MAC_ADDRESS_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_MAC_ADDRESS)
        
        // sbu unit 
        .antMatchers(HttpMethod.GET, EndpointURI.SUB_BUSINESS_UNITS)
        .hasAuthority(PermissionConstants.GET_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.PUT, EndpointURI.SUB_BUSINESS_UNIT)
        .hasAuthority(PermissionConstants.EDIT_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.POST, EndpointURI.SUB_BUSINESS_UNIT)
        .hasAuthority(PermissionConstants.ADD_NOTIFICATION_DAYS)
        .antMatchers(HttpMethod.DELETE, EndpointURI.SUB_BUSINESS_UNIT_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_NOTIFICATION_DAYS)
              
        // ratio config equations 
        .antMatchers(HttpMethod.GET, EndpointURI.RATIO_CONFIG_EQUATIONS)
        .hasAuthority(PermissionConstants.GET_RATIO_EQUATION)
        .antMatchers(HttpMethod.PUT, EndpointURI.RATIO_CONFIG_EQUATION)
        .hasAuthority(PermissionConstants.EDIT_RATIO_EQUATION)
        .antMatchers(HttpMethod.POST, EndpointURI.RATIO_CONFIG_EQUATION)
        .hasAuthority(PermissionConstants.ADD_RATIO_EQUATION)
        .antMatchers(HttpMethod.DELETE, EndpointURI.RATIO_CONFIG_EQUATION_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_RATIO_EQUATION)
        
        // ratio configs 
        .antMatchers(HttpMethod.GET, EndpointURI.RATIO_CONFIGS)
        .hasAuthority(PermissionConstants.GET_RATIO_CONFIG)
        .antMatchers(HttpMethod.PUT, EndpointURI.RATIO_CONFIG)
        .hasAuthority(PermissionConstants.EDIT_RATIO_CONFIG)
        .antMatchers(HttpMethod.POST, EndpointURI.RATIO_CONFIG)
        .hasAuthority(PermissionConstants.ADD_RATIO_CONFIG)
        .antMatchers(HttpMethod.DELETE, EndpointURI.RATIO_CONFIG_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_RATIO_CONFIG)
        
     // ratio config parameters
        .antMatchers(HttpMethod.GET, EndpointURI.RATIO_CONFIG_PARAMETERS)
        .hasAuthority(PermissionConstants.GET_RATIO_PARAMATER)
        .antMatchers(HttpMethod.PUT, EndpointURI.RATIO_CONFIG_PARAMETER)
        .hasAuthority(PermissionConstants.EDIT_RATIO_PARAMATER)
        .antMatchers(HttpMethod.POST, EndpointURI.RATIO_CONFIG_PARAMETER)
        .hasAuthority(PermissionConstants.ADD_RATIO_PARAMATER)
        .antMatchers(HttpMethod.DELETE, EndpointURI.RATIO_CONFIG_PARAMETER_BY_ID)
        .hasAuthority(PermissionConstants.DELETE_RATIO_PARAMATER)
        
        // edit key test
        .antMatchers(HttpMethod.GET, EndpointURI.CORE_TEST_CONFIGURE_TEST_ORIGIN)
        .hasAuthority(PermissionConstants.EDIT_KEY_TEST)
        .antMatchers(HttpMethod.GET, EndpointURI.CORE_TEST_CONFIGURE_UPDATE)
        .hasAuthority(PermissionConstants.EDIT_KEY_TEST_CONFIGURATION)
        
        .anyRequest().authenticated();
    // Add our custom JWT security filter
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().cacheControl();
  }
}
