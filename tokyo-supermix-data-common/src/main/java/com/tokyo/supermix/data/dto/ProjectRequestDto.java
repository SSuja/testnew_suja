package com.tokyo.supermix.data.dto;

import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProjectRequestDto {
  @NotNull(message = "{ProjectRequestDto.code.null}")
  @NotEmpty(message = "{ProjectRequestDto.code.empty}")
  private String code;
  @NotNull(message = "{ProjectRequestDto.name.null}")
  @NotEmpty(message = "{ProjectRequestDto.name.empty}")
  private String name;
  @NotNull(message = "{ProjectRequestDto.contactNumber.null}")
  @NotEmpty(message = "{ProjectRequestDto.contactNumber.empty}")
  private String contactNumber;
  private String contactPerson;
  private Date startDate;
  private Long customerId;
  @NotNull(message = "{ProjectRequestDto.plantCode.null}")
  @NotEmpty(message = "{ProjectRequestDto.plantCode.empty}")
  private String plantCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }
}
