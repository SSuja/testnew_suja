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
	
}
