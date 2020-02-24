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
@Table(schema = "tokyo-supermix", name = "mix_design_test_result")
public class MixDesignTestResult implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String testResult;
  private String trailNo;
  private String testStatus;
  @ManyToOne
  @JoinColumn(name = "mixDesignTestCode", nullable = false)
  private MixDesignTest mixDesignTest;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTestResult() {
    return testResult;
  }

  public void setTestResult(String testResult) {
    this.testResult = testResult;
  }

  public String getTrailNo() {
    return trailNo;
  }

  public void setTrailNo(String trailNo) {
    this.trailNo = trailNo;
  }

  public String getTestStatus() {
    return testStatus;
  }

  public void setTestStatus(String testStatus) {
    this.testStatus = testStatus;
  }

  public MixDesignTest getMixDesignTest() {
    return mixDesignTest;
  }

  public void setMixDesignTest(MixDesignTest mixDesignTest) {
    this.mixDesignTest = mixDesignTest;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
