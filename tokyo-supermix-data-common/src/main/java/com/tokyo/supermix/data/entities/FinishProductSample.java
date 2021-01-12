package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.Status;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private String finishProductCode;
  private Date date;
  @ManyToOne
  @JoinColumn(name = "plantEquipmentSerialNo", nullable = false)
  private PlantEquipment plantEquipment;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;
  private Status status;
  private String truckNo;
  @ManyToOne
  @JoinColumn(name = "projectCode", nullable = true)
  private Project project;
  @OneToOne
  @JoinColumn(name = "pourId", nullable = true)
  private Pour pour;
  private String workOrderNumber;
  @ManyToOne
	@JoinColumn(name = "userId", nullable = true)
	private User user;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFinishProductCode() {
    return finishProductCode;
  }

  public void setFinishProductCode(String finishProductCode) {
    this.finishProductCode = finishProductCode;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public PlantEquipment getPlantEquipment() {
    return plantEquipment;
  }

  public void setPlantEquipment(PlantEquipment plantEquipment) {
    this.plantEquipment = plantEquipment;
  }

  public MixDesign getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesign mixDesign) {
    this.mixDesign = mixDesign;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getTruckNo() {
    return truckNo;
  }

  public void setTruckNo(String truckNo) {
    this.truckNo = truckNo;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Pour getPour() {
    return pour;
  }

  public void setPour(Pour pour) {
    this.pour = pour;
  }

  public String getWorkOrderNumber() {
    return workOrderNumber;
  }

  public void setWorkOrderNumber(String workOrderNumber) {
    this.workOrderNumber = workOrderNumber;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}
  
}
