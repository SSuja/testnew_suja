package com.tokyo.supermix.data.dto;
import java.sql.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProcessSampleLoadRequestDto {
  private Long id;
  @NotNull(message = "{processSampleLoadRequestDto.vehicleNo.null}")
  private String vehicleNo;
  @NotNull(message = "{processSampleLoadRequestDto.unitId.null}")
  private Long unitId;
  @NotNull(message = "{processSampleLoadRequestDto.quantity.null}")
  private Long quantity;
  @NotNull(message = "{processSampleLoadRequestDto.date.null}")
  private Date date;
  @NotNull(message = "{processSampleLoadRequestDto.expiryDate.null}")
  private Date expiryDate;
  @NotNull(message = "{processSampleLoadRequestDto.processSampleCode.null}")
  @NotEmpty(message = "{processSampleLoadRequestDto.processSampleCode.empty}")
  private String processSampleCode;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getVehicleNo() {
    return vehicleNo;
  }
  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }
  public Long getUnitId() {
    return unitId;
  }
  public void setUnitId(Long unitId) {
    this.unitId = unitId;
  }
  public Long getQuantity() {
    return quantity;
  }
  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
  public Date getDate() {
    return date;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Date getExpiryDate() {
    return expiryDate;
  }
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
  public String getProcessSampleCode() {
    return processSampleCode;
  }
  public void setProcessSampleCode(String processSampleCode) {
    this.processSampleCode = processSampleCode;
  }
}
