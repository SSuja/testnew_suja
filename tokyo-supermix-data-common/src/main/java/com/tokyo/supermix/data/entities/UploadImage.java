package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "upload_image")
public class UploadImage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String testImage;
  private String name;
  @ManyToOne
  @JoinColumn(name = "materialTestCode", nullable = true)
  private MaterialTest materialTest;
  @ManyToOne
  @JoinColumn(name = "finishProductTestCode", nullable = true)
  private FinishProductTest finishProductTest;
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTestImage() {
    return testImage;
  }

  public void setTestImage(String testImage) {
    this.testImage = testImage;
  }

  public MaterialTest getMaterialTest() {
    return materialTest;
  }

  public void setMaterialTest(MaterialTest materialTest) {
    this.materialTest = materialTest;
  }

  public FinishProductTest getFinishProductTest() {
    return finishProductTest;
  }

  public void setFinishProductTest(FinishProductTest finishProductTest) {
    this.finishProductTest = finishProductTest;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
