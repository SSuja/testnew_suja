package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.EmailNotifications;

@Entity
@Table(schema = "tokyo-supermix", name = "email_group")
public class EmailGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Enumerated(EnumType.ORDINAL)
	private EmailNotifications emailNotifications;
	private boolean schedule;
	private static final long serialVersionUID = 1L;

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

	
	public EmailNotifications getEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(EmailNotifications emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSchedule() {
		return schedule;
	}

	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
}
