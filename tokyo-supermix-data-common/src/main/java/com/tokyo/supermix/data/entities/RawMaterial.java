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
import com.tokyo.supermix.data.enums.MaterialType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "raw_material")
public class RawMaterial extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  @ManyToOne
  @JoinColumn(name = "materialStateId", nullable = false)
  private MaterialState materialState;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = false)
  private MaterialSubCategory materialSubCategory;
  private String description;
  private String prefix;
  private boolean active;
  @ManyToOne
  @JoinColumn(name = "plantCode", nullable = true)
  private Plant plant;
  private String erpCode;
  @ManyToOne
  @JoinColumn(name = "subBusinessUnitId", nullable = true)
  private SubBusinessUnit subBusinessUnit;
  @Enumerated(EnumType.ORDINAL)
  private MaterialType materialType;
  private boolean sentMail;
}
