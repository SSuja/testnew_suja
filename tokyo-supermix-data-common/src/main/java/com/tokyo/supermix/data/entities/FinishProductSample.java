package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample")
public class FinishProductSample implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private String code;
  private Date dateAndTime;
  private String salesOrderNo;
  @ManyToOne
  @JoinColumn(name = "mixDesignCode", nullable = false)
  private MixDesign mixDesign;
  @ManyToOne
  @JoinColumn(name = "projectCode", nullable = false)
  private Project project;
  @OneToOne
  @JoinColumn(name = "pourId", nullable = false)
  private Pour pour;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getDateAndTime() {
    return dateAndTime;
  }

  public void setDateAndTime(Date dateAndTime) {
    this.dateAndTime = dateAndTime;
  }

  public String getSalesOrderNo() {
    return salesOrderNo;
  }

  public void setSalesOrderNo(String salesOrderNo) {
    this.salesOrderNo = salesOrderNo;
  }

  public MixDesign getMixDesign() {
    return mixDesign;
  }

  public void setMixDesign(MixDesign mixDesign) {
    this.mixDesign = mixDesign;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
