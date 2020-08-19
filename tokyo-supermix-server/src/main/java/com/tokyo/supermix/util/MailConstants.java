package com.tokyo.supermix.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * contains custom Email Address
 *
 */

@Component
@PropertySource("classpath:mail.properties")
public class MailConstants {
	@Value("${mail.notificationTime.plantEquipment}")
    private String plantEquipmentTime;
	@Value("${mail.notificationTime.strengthTestMixDesign}")
    private String strengthTestMixDesign;
	
    public String getPlantEquipmentTime() {
      return plantEquipmentTime;
    }
    public void setPlantEquipmentTime(String plantEquipmentTime) {
      this.plantEquipmentTime = plantEquipmentTime;
    }
    public String getStrengthTestMixDesign() {
      return strengthTestMixDesign;
    }
    public void setStrengthTestMixDesign(String strengthTestMixDesign) {
      this.strengthTestMixDesign = strengthTestMixDesign;
    }   
    
}
