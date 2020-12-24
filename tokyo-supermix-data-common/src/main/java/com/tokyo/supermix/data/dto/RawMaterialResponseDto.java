package com.tokyo.supermix.data.dto;

public class RawMaterialResponseDto {
  private Long id;
  private String name;
  private MaterialStateDto materialState;
  private MaterialSubCategoryResponseDto materialSubCategory;
  private String description;
  private String prefix;
  private boolean active;
  private PlantDto plant;
  private String erpCode;
  private Long subBusinessUnitId;
  private String subBusinessUnitName;

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

  public MaterialStateDto getMaterialState() {
    return materialState;
  }

  public void setMaterialState(MaterialStateDto materialState) {
    this.materialState = materialState;
  }

  public MaterialSubCategoryResponseDto getMaterialSubCategory() {
    return materialSubCategory;
  }

  public void setMaterialSubCategory(MaterialSubCategoryResponseDto materialSubCategory) {
    this.materialSubCategory = materialSubCategory;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public PlantDto getPlant() {
    return plant;
  }

  public void setPlant(PlantDto plant) {
    this.plant = plant;
  }

  public String getErpCode() {
    return erpCode;
  }

  public void setErpCode(String erpCode) {
    this.erpCode = erpCode;
  }

  public Long getSubBusinessUnitId() {
    return subBusinessUnitId;
  }

  public void setSubBusinessUnitId(Long subBusinessUnitId) {
    this.subBusinessUnitId = subBusinessUnitId;
  }

  public String getSubBusinessUnitName() {
    return subBusinessUnitName;
  }

  public void setSubBusinessUnitName(String subBusinessUnitName) {
    this.subBusinessUnitName = subBusinessUnitName;
  }
}
