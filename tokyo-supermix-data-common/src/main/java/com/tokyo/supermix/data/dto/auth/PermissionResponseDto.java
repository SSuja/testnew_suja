package com.tokyo.supermix.data.dto.auth;

public class PermissionResponseDto {
  private Long id;
  private String name;
  private Boolean status;
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
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }
}
