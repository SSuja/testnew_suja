package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.EmailNotifications;

public class EmailGroupDto {
	private Long id;
	private String name;
	private EmailNotifications emailNotifications;
	private boolean schedule;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
	public EmailNotifications getEmailNotifications() {
		return emailNotifications;
	}
	public void setEmailNotifications(EmailNotifications emailNotifications) {
		this.emailNotifications = emailNotifications;
	}
	
	

}
