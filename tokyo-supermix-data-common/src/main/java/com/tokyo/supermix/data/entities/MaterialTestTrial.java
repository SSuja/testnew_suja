package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test_trial")
public class MaterialTestTrial extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Long trialNo;
  private Date trialDate;
  @ManyToOne
  @JoinColumn(name = "materialTestCode", nullable = false)
  private MaterialTest materialTest;
  @ManyToOne
  @JoinColumn(name = "testEquationId", nullable = false)
  private TestEquation testEquation;
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
  public Date getTrialDate() {
    return trialDate;
  }
  public void setTrialDate(Date trialDate) {
    this.trialDate = trialDate;
  }
  public MaterialTest getMaterialTest() {
    return materialTest;
  }
  public void setMaterialTest(MaterialTest materialTest) {
    this.materialTest = materialTest;
  }
  public TestEquation getTestEquation() {
    return testEquation;
  }
  public void setTestEquation(TestEquation testEquation) {
    this.testEquation = testEquation;
  }
  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
