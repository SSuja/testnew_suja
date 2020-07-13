package com.tokyo.supermix.data.dto.report;

import java.sql.Date;

public class IncomingSampleReportDto {
  private String code;
  private Date date;
  private String vehicleNo;
  private String rawMaterialName;
  private String materialCategory;
  private String materialSubCategory;
  private String status;

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

  public String getRawMaterialName() {
    return rawMaterialName;
  }

  public void setRawMaterialName(String rawMaterialName) {
    this.rawMaterialName = rawMaterialName;
  }

  public String getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(String materialCategory) {
    this.materialCategory = materialCategory;
  }

  public String getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(String materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}
