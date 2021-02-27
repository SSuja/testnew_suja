package com.tokyo.supermix.data.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.DateAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "core_test_configure")
public class CoreTestConfigure extends DateAudit implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = false)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "rawMaterialId", nullable = true)
  private RawMaterial rawMaterial;
  @ManyToOne
  @JoinColumn(name = "testConfigureId", nullable = false)
  private TestConfigure testConfigure;
  private boolean coreTest;
  private boolean applicableTest;
}
