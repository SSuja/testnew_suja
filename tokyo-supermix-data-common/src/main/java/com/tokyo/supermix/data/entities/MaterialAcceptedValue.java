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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.MaterialParamType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "material_accepted_value")
public class MaterialAcceptedValue extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  @OneToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  @ManyToOne
  @JoinColumn(name = "testEquationId", nullable = true)
  private TestEquation testEquation;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @Enumerated(EnumType.ORDINAL)
  private Condition conditionRange;
  @ManyToOne
  @JoinColumn(name = "testParameterId", nullable = true)
  private TestParameter testParameter;
  private boolean finalResult;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @Enumerated(EnumType.ORDINAL)
  private CategoryAcceptedType categoryAcceptedType;
  @ManyToOne
  @JoinColumn(name = "materialQualityParameterId", nullable = true)
  private MaterialQualityParameter materialQualityParameter;
  @Enumerated(EnumType.ORDINAL)
  private MaterialParamType materialParamType;
}
