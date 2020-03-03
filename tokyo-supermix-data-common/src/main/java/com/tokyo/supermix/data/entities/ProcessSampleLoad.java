package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "process_sample_load")
public class ProcessSampleLoad implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String vehicleNo;
  private String measurement;
  private Long quantity;
  private Date dateAndTime;
  private Date expiryDate;
  @ManyToOne
  @JoinColumn(name = "processSampleCode", nullable = false)
  private ProcessSample processSample;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getVehicleNo() {
    return vehicleNo;
  }

  public void setVehicleNo(String vehicleNo) {
    this.vehicleNo = vehicleNo;
  }

  public String getMeasurement() {
    return measurement;
  }

  public void setMeasurement(String measurement) {
    this.measurement = measurement;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Date getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Date dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public ProcessSample getProcessSample() {
    return processSample;
  }

  public void setProcessSample(ProcessSample processSample) {
    this.processSample = processSample;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
