package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.TestLevel;

@Entity
@Table(schema = "tokyo-supermix", name = "material_test")
public class MaterialTest extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String code;
  private Long noOfTrial;
  private String comment;
  @Enumerated(EnumType.ORDINAL)
  private Status status;
  @Enumerated(EnumType.ORDINAL)
  private TestLevel testLevel;
  @ManyToOne
  @JoinColumn(name = "incomingSampleCode", nullable = false)
  private IncomingSample incomingSample;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;
  private String specimenCode;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public TestLevel getTestLevel() {
    return testLevel;
  }

  public void setTestLevel(TestLevel testLevel) {
    this.testLevel = testLevel;
  }

  public IncomingSample getIncomingSample() {
    return incomingSample;
  }

  public void setIncomingSample(IncomingSample incomingSample) {
    this.incomingSample = incomingSample;
  }

  public TestConfigure getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }

  public MaterialState getMaterialState() {
    return materialState;
  }

  public void setMaterialState(MaterialState materialState) {
    this.materialState = materialState;
  }

  public String getSpecimenCode() {
    return specimenCode;
  }

  public void setSpecimenCode(String specimenCode) {
    this.specimenCode = specimenCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
