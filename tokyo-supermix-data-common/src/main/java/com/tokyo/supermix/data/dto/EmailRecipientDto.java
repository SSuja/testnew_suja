package com.tokyo.supermix.data.dto;

import java.util.List;
import com.tokyo.supermix.data.enums.RecipientType;

public class EmailRecipientDto {
  private Long id;
  private Long emailGroupId;
  private List<Long> plantRoleId;
  private List<Long> userId;
  private RecipientType recipientType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEmailGroupId() {
    return emailGroupId;
  }

  public void setEmailGroupId(Long emailGroupId) {
    this.emailGroupId = emailGroupId;
  }

  public List<Long> getPlantRoleId() {
    return plantRoleId;
  }

  public void setPlantRoleId(List<Long> plantRoleId) {
    this.plantRoleId = plantRoleId;
  }

  public List<Long> getUserId() {
    return userId;
  }

  public void setUserId(List<Long> userId) {
    this.userId = userId;
  }

  public RecipientType getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(RecipientType recipientType) {
    this.recipientType = recipientType;
  }
}
