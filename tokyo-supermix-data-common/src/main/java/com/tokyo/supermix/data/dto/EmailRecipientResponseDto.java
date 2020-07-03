package com.tokyo.supermix.data.dto;

import com.tokyo.supermix.data.enums.RecipientType;

public class EmailRecipientResponseDto {
  private Long id;
  private Long plantRoleId;
  private Long userId;
  private String userName;
  private String plantRoleName;
  private Long emailGroupId;
  private RecipientType recipientType;
  private String plantCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPlantRoleId() {
    return plantRoleId;
  }

  public void setPlantRoleId(Long plantRoleId) {
    this.plantRoleId = plantRoleId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getEmailGroupId() {
    return emailGroupId;
  }

  public void setEmailGroupId(Long emailGroupId) {
    this.emailGroupId = emailGroupId;
  }

  public RecipientType getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(RecipientType recipientType) {
    this.recipientType = recipientType;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPlantRoleName() {
    return plantRoleName;
  }

  public void setPlantRoleName(String plantRoleName) {
    this.plantRoleName = plantRoleName;
  }  
}
