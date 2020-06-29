package com.tokyo.supermix.data.dto;

public class EmailRecipientRequestDto {
  private Long id;
  private Long plantRoleId;
  private Long userId;
  private Long emailGroupId;

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
}
