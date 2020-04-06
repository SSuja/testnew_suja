package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "finish_product_sample_issues")
public class FinishProductSampleIssues implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String truckNo;
  @ManyToOne
  @JoinColumn(name = "finishProductSampleId", nullable = false)
  private FinishProductSample finishProductSample;
  @ManyToOne
  @JoinColumn(name = "projectCode", nullable = false)
  private Project project;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTruckNo() {
    return truckNo;
  }

  public void setTruckNo(String truckNo) {
    this.truckNo = truckNo;
  }

  public FinishProductSample getFinishProductSample() {
    return finishProductSample;
  }

  public void setFinishProductSample(FinishProductSample finishProductSample) {
    this.finishProductSample = finishProductSample;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
