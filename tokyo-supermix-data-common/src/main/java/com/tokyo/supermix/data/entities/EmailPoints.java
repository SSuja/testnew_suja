package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
  @JoinColumn(name = "testId", nullable = false)
  private Test test;
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public boolean isActive() {
    return active;
  }
  public void setActive(boolean active) {
    this.active = active;
  }
  public MaterialSubCategory getMaterialSubCategory() {
    return materialSubCategory;
  }
  public void setMaterialSubCategory(MaterialSubCategory materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }
  public MaterialCategory getMaterialCategory() {
    return materialCategory;
  }
  public void setMaterialCategory(MaterialCategory materialCategory) {
    this.materialCategory = materialCategory;
  }
  public Test getTest() {
    return test;
  }
  public void setTest(Test test) {
    this.test = test;
  }
}
