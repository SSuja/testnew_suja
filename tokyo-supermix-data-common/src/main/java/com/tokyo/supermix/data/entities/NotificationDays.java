package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "notification_days")
public class NotificationDays {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "emailGroupId", nullable = false)
  private EmailGroup emailGroup;
  private Double days;
  private static final long serialVersionUID = 1L;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public EmailGroup getEmailGroup() {
    return emailGroup;
  }

  public void setEmailGroup(EmailGroup emailGroup) {
    this.emailGroup = emailGroup;
  }

  public Double getDays() {
    return days;
  }

  public void setDays(Double days) {
    this.days = days;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
