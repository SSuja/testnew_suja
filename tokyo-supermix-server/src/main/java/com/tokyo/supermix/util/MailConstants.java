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
   
	
}
