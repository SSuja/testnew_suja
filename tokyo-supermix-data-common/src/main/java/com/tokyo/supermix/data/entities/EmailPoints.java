package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "tokyo-supermix", name = "email_points")
public class EmailPoints {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private boolean active;
  @ManyToOne
  @JoinColumn(name = "materialSubCategoryId", nullable = true)
  private MaterialSubCategory materialSubCategory;
  @ManyToOne
  @JoinColumn(name = "materialCategoryId", nullable = true)
  private MaterialCategory materialCategory;
  @ManyToOne
  @JoinColumn(name = "testId", nullable = true)
  private Test test;
  @OneToOne
  @JoinColumn(name = "testConfigureId", nullable = true)
  private TestConfigure testConfigure;
  private boolean adminLevelEmailConfiguration;
  private boolean schedule;
}
