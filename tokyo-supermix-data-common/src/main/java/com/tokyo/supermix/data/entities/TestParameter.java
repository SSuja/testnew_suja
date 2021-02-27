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
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.TestParameterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
  private String dateValue;
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
}
