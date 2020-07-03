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
	@Value("${mail.notify.newUser}")
	private String[] mailNewUser;
	@Value("${mail.notify.equipmentCalibration}")
	private String[] mailEquipmentCalibration;
	@Value("${mail.notify.incomingSamplePerDay}")
	private String[] mailIncomingSamplePerDay;
	@Value("${mail.notify.admixureExpiry}")
	private String[] mailAdmixureExpiry;
	@Value("${mail.notify.alertMixDedisgnTest}")
	private String[] mailalertMixDedisgnTest;
	@Value("${mail.notify.mailPendingIncomingSample}")
	private String[] mailPendingIncomingSample;
	@Value("${mail.notify.mailNewProject}")
	private String[] mailNewProject;
	@Value("${mail.notify.mailNewFinishProduct}")
    private String[] mailNewFinishProduct;
	@Value("${mail.notify.mailCongreteStrengthTestStatus}")
    private String[] mailCongreteStrengthTestStatus;
	@Value("${mail.notify.mailNewConcreteTest}")
	private String[] mailNewConcreteTest;
	@Value("${mail.notify.mailUpdateIncomingSampleStatus}")
    private String[] mailUpdateIncomingSampleStatus;
	@Value("${mail.notify.mailUpdateMaterialTestStatus}")
    private String[] mailUpdateMaterialTestStatus;
	@Value("${mail.notify.mailNewSeiveTest}")
    private String[] mailNewSeiveTest;
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
	public String[] getMailNewUser() {
		return mailNewUser;
	}
	public void setMailNewUser(String[] mailNewUser) {
		this.mailNewUser = mailNewUser;
	}
	public String[] getMailEquipmentCalibration() {
		return mailEquipmentCalibration;
	}
	public void setMailEquipmentCalibration(String[] mailEquipmentCalibration) {
		this.mailEquipmentCalibration = mailEquipmentCalibration;
	}
	public String[] getMailIncomingSamplePerDay() {
		return mailIncomingSamplePerDay;
	}
	public void setMailIncomingSamplePerDay(String[] mailIncomingSamplePerDay) {
		this.mailIncomingSamplePerDay = mailIncomingSamplePerDay;
	}
	
	public String[] getMailAdmixureExpiry() {
		return mailAdmixureExpiry;
	}
	public void setMailAdmixureExpiry(String[] mailAdmixureExpiry) {
		this.mailAdmixureExpiry = mailAdmixureExpiry;
	}
	public String[] getMailalertMixDedisgnTest() {
		return mailalertMixDedisgnTest;
	}
	public void setMailalertMixDedisgnTest(String[] mailalertMixDedisgnTest) {
		this.mailalertMixDedisgnTest = mailalertMixDedisgnTest;
	}
	public String[] getMailPendingIncomingSample() {
		return mailPendingIncomingSample;
	}
	public void setMailPendingIncomingSample(String[] mailPendingIncomingSample) {
		this.mailPendingIncomingSample = mailPendingIncomingSample;
	}
	public String[] getMailNewProject() {
		return mailNewProject;
	}
	public void setMailNewProject(String[] mailNewProject) {
		this.mailNewProject = mailNewProject;
	}
    public String[] getMailNewFinishProduct() {
      return mailNewFinishProduct;
    }
    public void setMailNewFinishProduct(String[] mailNewFinishProduct) {
      this.mailNewFinishProduct = mailNewFinishProduct;
    }
    public String[] getMailCongreteStrengthTestStatus() {
      return mailCongreteStrengthTestStatus;
    }
    public void setMailCongreteStrengthTestStatus(String[] mailCongreteStrengthTestStatus) {
      this.mailCongreteStrengthTestStatus = mailCongreteStrengthTestStatus;
    }
    public String[] getMailNewConcreteTest() {
      return mailNewConcreteTest;
    }
    public void setMailNewConcreteTest(String[] mailNewConcreteTest) {
      this.mailNewConcreteTest = mailNewConcreteTest;
    }
    public String[] getMailUpdateIncomingSampleStatus() {
      return mailUpdateIncomingSampleStatus;
    }
    public void setMailUpdateIncomingSampleStatus(String[] mailUpdateIncomingSampleStatus) {
      this.mailUpdateIncomingSampleStatus = mailUpdateIncomingSampleStatus;
    }
    public String[] getMailUpdateMaterialTestStatus() {
      return mailUpdateMaterialTestStatus;
    }
    public void setMailUpdateMaterialTestStatus(String[] mailUpdateMaterialTestStatus) {
      this.mailUpdateMaterialTestStatus = mailUpdateMaterialTestStatus;
    }
    public String[] getMailNewSeiveTest() {
      return mailNewSeiveTest;
    }
    public void setMailNewSeiveTest(String[] mailNewSeiveTest) {
      this.mailNewSeiveTest = mailNewSeiveTest;
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
