package com.tokyo.supermix.data.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class SupplierCategoryDto {

  private Long id;
  @NotNull(message = "{supplierCategoryDto.category.blank}")
  private String category;
  @Column(nullable = true)
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
