package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.data.enums.TestResultType;

@Entity
@Table(schema = "tokyo-supermix", name = "test_configure")
public class TestConfigure extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private boolean coreTest;
  @Size(min = 3 , max = 500)
  private String description;
  @Size(min = 3 , max = 500)
  private String testProcedure;
  private String prefix;
  @Enumerated(EnumType.ORDINAL)
  private MainType testType;
  @Enumerated(EnumType.ORDINAL)
  private ReportFormat reportFormat;
  @ManyToOne
  @JoinColumn(name = "testId", nullable = false)
  private Test test;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @Enumerated(EnumType.ORDINAL)
  private AcceptedType acceptedType;
  private Long noOfTrial;
  private boolean name;
  private String dueDay;
  @Enumerated(EnumType.ORDINAL)
  private TestResultType testResultType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isCoreTest() {
    return coreTest;
  }

  public void setCoreTest(boolean coreTest) {
    this.coreTest = coreTest;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTestProcedure() {
    return testProcedure;
  }

  public void setTestProcedure(String testProcedure) {
    this.testProcedure = testProcedure;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public MainType getTestType() {
    return testType;
  }

  public void setTestType(MainType testType) {
    this.testType = testType;
  }

  public ReportFormat getReportFormat() {
    return reportFormat;
  }

  public void setReportFormat(ReportFormat reportFormat) {
    this.reportFormat = reportFormat;
  }

  public Test getTest() {
    return test;
  }

  public void setTest(Test test) {
    this.test = test;
  }

  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }

  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }

  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public RawMaterial getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterial rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public AcceptedType getAcceptedType() {
    return acceptedType;
  }

  public void setAcceptedType(AcceptedType acceptedType) {
    this.acceptedType = acceptedType;
  }

  public Long getNoOfTrial() {
    return noOfTrial;
  }

  public void setNoOfTrial(Long noOfTrial) {
    this.noOfTrial = noOfTrial;
  }

  public boolean isName() {
    return name;
  }

  public void setName(boolean name) {
    this.name = name;
  }

  public String getDueDay() {
    return dueDay;
  }

  public void setDueDay(String dueDay) {
    this.dueDay = dueDay;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public TestResultType getTestResultType() {
    return testResultType;
  }

  public void setTestResultType(TestResultType testResultType) {
    this.testResultType = testResultType;
  }
}
