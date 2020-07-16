package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.enums.RecipientType;

@Entity
@Table(schema = "tokyo-supermix", name = "email_recipient")
public class EmailRecipient extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "emailGroupId", nullable = false)
  private EmailGroup emailGroup;
  @ManyToOne
  @JoinColumn(name = "plantRoleId", nullable = true)
  private PlantRole plantRole;
  @ManyToOne
  @JoinColumn(name = "userId", nullable = true)
  private User user;
  @Enumerated(EnumType.ORDINAL)
  private RecipientType recipientType;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = false)
  private Plant plant;

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

  public PlantRole getPlantRole() {
    return plantRole;
  }

  public void setPlantRole(PlantRole plantRole) {
    this.plantRole = plantRole;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public RecipientType getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(RecipientType recipientType) {
    this.recipientType = recipientType;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }
}
