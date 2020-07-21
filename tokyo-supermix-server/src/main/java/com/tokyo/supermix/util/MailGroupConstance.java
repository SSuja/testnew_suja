package com.tokyo.supermix.util;

import org.springframework.stereotype.Component;

@Component
public class MailGroupConstance {
  
  /* email group name */
  public static final String MIX_DESIGN_EMAIL_GROUP = "Mix Design";
  public static final String PLANT_EQUIPMENT_CALIBRATION_GROUP = "Plant Equipment Calibration";
  public static final String CREATE_PROJECT = "create project";
  public static final String CREATE_PLANT_EQUIPMENT = "Plant Equipment Creation";
  public static final String CREATE_CUSTOMER = "Customer Creation";
  
  private MailGroupConstance() {}

}
