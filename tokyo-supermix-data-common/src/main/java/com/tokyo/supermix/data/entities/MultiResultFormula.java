package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "multi_result_formula")
public class MultiResultFormula {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  private String logicalFormula;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public TestConfigure getTestConfigure() {
    return testConfigure;
  }

  public void setTestConfigure(TestConfigure testConfigure) {
    this.testConfigure = testConfigure;
  }

  public String getLogicalFormula() {
    return logicalFormula;
  }

  public void setLogicalFormula(String logicalFormula) {
    this.logicalFormula = logicalFormula;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
