package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test_trial")
public class MaterialTestTrial implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Long trialNo;
  private Double result;
  @ManyToOne
  @JoinColumn(name = "materialTestId", nullable = false)
  private MaterialTest materialTest;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getTrialNo() {
    return trialNo;
  }

  public void setTrialNo(Long trialNo) {
    this.trialNo = trialNo;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public MaterialTest getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTest materialTest) {
    this.materialTest = materialTest;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
