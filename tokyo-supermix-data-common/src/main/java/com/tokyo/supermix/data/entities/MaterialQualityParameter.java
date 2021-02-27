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
import com.tokyo.supermix.data.entities.auth.DateAudit;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "material_quality_parameter")
public class MaterialQualityParameter extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Double minValue;
  private Double maxValue;
  private Double value;
  @Enumerated(EnumType.ORDINAL)
  private Condition conditionRange;
  @ManyToOne
  @JoinColumn(name = "unitId", nullable = false)
  private Unit unit;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = true)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "parameterId", nullable = false)
  private Parameter parameter;
  @Enumerated(EnumType.ORDINAL)
  private QualityParamaterType qualityParamaterType;
}
