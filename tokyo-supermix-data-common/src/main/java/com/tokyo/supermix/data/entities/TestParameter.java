package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;

@Entity
@Table(schema = "tokyo-supermix", name = "test_parameter")
public class TestParameter extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "parameterId", nullable = true)
  private Parameter parameter;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;
  @Size(max = 2)
  private String abbreviation;
  @Enumerated(EnumType.ORDINAL)
  private TestParameterType type;
  private Double value;
  private LocalDateTime dateValue;
  private String name;
  private String groupKey;
  @ManyToOne
  @JoinColumn(name = "qualityParameterId", nullable = true)
  private QualityParameter qualityParameter;
  private boolean acceptedCriteria;
  @Enumerated(EnumType.ORDINAL)
  private InputMethod inputMethods;
  private String level;
  @ManyToOne
  @JoinColumn(name = "tableFormatId", nullable = true)
  private TableFormat tableFormat;

  public TableFormat getTableFormat() {
    return tableFormat;
  }

  public void setTableFormat(TableFormat tableFormat) {
    this.tableFormat = tableFormat;
  }

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

  public Parameter getParameter() {
    return parameter;
  }

  public void setParameter(Parameter parameter) {
    this.parameter = parameter;
  }

  public Unit getUnit() {
    return unit;
  }

  public void setUnit(Unit unit) {
    this.unit = unit;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public TestParameterType getType() {
    return type;
  }

  public void setType(TestParameterType type) {
    this.type = type;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGroupKey() {
    return groupKey;
  }

  public void setGroupKey(String groupKey) {
    this.groupKey = groupKey;
  }

  public QualityParameter getQualityParameter() {
    return qualityParameter;
  }

  public void setQualityParameter(QualityParameter qualityParameter) {
    this.qualityParameter = qualityParameter;
  }

  public boolean isAcceptedCriteria() {
    return acceptedCriteria;
  }

  public void setAcceptedCriteria(boolean acceptedCriteria) {
    this.acceptedCriteria = acceptedCriteria;
  }

  public InputMethod getInputMethods() {
    return inputMethods;
  }

  public void setInputMethods(InputMethod inputMethods) {
    this.inputMethods = inputMethods;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public LocalDateTime getDateValue() {
    return dateValue;
  }

  public void setDateValue(LocalDateTime dateValue) {
    this.dateValue = dateValue;
  }
}
