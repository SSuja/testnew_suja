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
	@Value("${mail.notify.admin}")
	private String admin;

	@Value("${mail.notify.report}")
    private String[] mailReportUser;
	@Value("${mail.notificationtime.plantEquipment}")
    private String plantEquipmentTime;
	@Value("${mail.notificationtime.strengthTestMixDesign}")
    private String strengthTestMixDesign;
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}

    public String[] getMailReportUser() {
      return mailReportUser;
    }
    public void setMailReportUser(String[] mailReportUser) {
      this.mailReportUser = mailReportUser;
    }
    public String getPlantEquipmentTime() {
      return plantEquipmentTime;
    }
    public void setPlantEquipmentTime(String plantEquipmentTime) {
      this.plantEquipmentTime = plantEquipmentTime;
    }    
}
