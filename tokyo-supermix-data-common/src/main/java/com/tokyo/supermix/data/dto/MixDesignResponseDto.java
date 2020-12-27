package com.tokyo.supermix.data.dto;

import java.sql.Date;
import com.tokyo.supermix.data.enums.Status;

public class MixDesignResponseDto {
  private String code;
  private Date date;
  private String plantCode;
  private String plantName;
  private Status status;
  private String createdAt;
  private String updatedAt;
  private boolean checkDepend;
  private String rawMaterialName;
  private Long rawMaterialId;
  private Long rawMaterialMaterialSubCategoryId;
  private boolean isApproved;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public String getPlantName() {
    return plantName;
  }

  public void setPlantName(String plantName) {
    this.plantName = plantName;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isCheckDepend() {
    return checkDepend;
  }

  public void setCheckDepend(boolean checkDepend) {
    this.checkDepend = checkDepend;
  }

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public Long getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(Long rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Long getRawMaterialMaterialSubCategoryId() {
    return rawMaterialMaterialSubCategoryId;
  }

  public void setRawMaterialMaterialSubCategoryId(Long rawMaterialMaterialSubCategoryId) {
    this.rawMaterialMaterialSubCategoryId = rawMaterialMaterialSubCategoryId;
  }

public boolean isApproved() {
	return isApproved;
}

public void setApproved(boolean isApproved) {
	this.isApproved = isApproved;
}
}
