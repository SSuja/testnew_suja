package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.tokyo.supermix.data.enums.MaterialType;

public class RawMaterialRequestDto {
  private Long id;
  @NotNull(message = "{rawMaterialRequestDto.name.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.name.empty}")
  private String name;
  private Long materialStateId;
  private Long materialSubCategoryId;
  private String description;
  @NotNull(message = "{rawMaterialRequestDto.prefix.null}")
  @NotEmpty(message = "{rawMaterialRequestDto.prefix.empty}")
  private String prefix;
  private boolean active;
  private String plantCode;
  private String erpCode;
  private Long subBusinessUnitId;
  private MaterialType materialType;
  private boolean sentMail;

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

  public Long getMaterialStateId() {
    return materialStateId;
  }

  public void setMaterialStateId(Long materialStateId) {
    this.materialStateId = materialStateId;
  }

  public Long getMaterialSubCategoryId() {
    return materialSubCategoryId;
  }

  public void setMaterialSubCategoryId(Long materialSubCategoryId) {
    this.materialSubCategoryId = materialSubCategoryId;
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

  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
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

  public MaterialType getMaterialType() {
    return materialType;
  }

  public void setMaterialType(MaterialType materialType) {
    this.materialType = materialType;
  }

  public boolean isSentMail() {
    return sentMail;
  }

  public void setSentMail(boolean sentMail) {
    this.sentMail = sentMail;
  }
}
