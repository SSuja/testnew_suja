
package com.tokyo.supermix.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * contains custom error messages
 *
 */

@Component
@PropertySource("classpath:ValidationMessages.properties")
public class ValidationFailureStatusCodes {
  /*
   * Plant
   */
  @Value("${validation.plant.notExists}")
  private String plantNotExist;

  @Value("${validation.plant.alreadyExist}")
  private String plantAlreadyExist;

  @Value("${validation.plantName.alreadyExist}")
  private String plantNameAlreadyExist;

  @Value("${validation.plantId.alreadyExist}")
  private String plantIdAlreadyExist;

  @Value("${plantDto.name.null")
  private String plantNameIsNull;

  @Value("${plantDto.name.empty")
  private String plantNameIsEmpty;

  @Value("${plantDto.id.null")
  private String plantIdIsNull;

  public String getPlantNotExist() {
    return plantNotExist;
  }

  public void setPlantNotExist(String plantNotExist) {
    this.plantNotExist = plantNotExist;
  }

  public String getPlantAlreadyExist() {
    return plantAlreadyExist;
  }

  public void setPlantAlreadyExist(String plantAlreadyExist) {
    this.plantAlreadyExist = plantAlreadyExist;
  }

  // for designation

  @Value("${validation.designation.notExists}")
  private String designationNotExist;

  @Value("${validation.designation.alreadyExist}")
  private String designationAlreadyExist;

  public String getDesignationNotExist() {
    return designationNotExist;
  }

  public void setDesignationNotExist(String designationNotExist) {
    this.designationNotExist = designationNotExist;
  }

  public String getDesignationAlreadyExist() {
    return designationAlreadyExist;
  }

  public void setDesignationAlreadyExist(String designationAlreadyExist) {
    this.designationAlreadyExist = designationAlreadyExist;
  }

  public String getPlantNameIsNull() {
    return plantNameIsNull;
  }

  public void setPlantNameIsNull(String plantNameIsNull) {
    this.plantNameIsNull = plantNameIsNull;
  }

  public String getPlantNameIsEmpty() {
    return plantNameIsEmpty;
  }

  public void setPlantNameIsEmpty(String plantNameIsEmpty) {
    this.plantNameIsEmpty = plantNameIsEmpty;
  }

  public String getPlantIdIsNull() {
    return plantIdIsNull;
  }

  public void setPlantIdIsNull(String plantIdIsNull) {
    this.plantIdIsNull = plantIdIsNull;
  }

  public String getPlantNameAlreadyExist() {
    return plantNameAlreadyExist;
  }

  public void setPlantNameAlreadyExist(String plantNameAlreadyExist) {
    this.plantNameAlreadyExist = plantNameAlreadyExist;
  }

  public String getPlantIdAlreadyExist() {
    return plantIdAlreadyExist;
  }

  public void setPlantIdAlreadyExist(String plantIdAlreadyExist) {
    this.plantIdAlreadyExist = plantIdAlreadyExist;
  }

  /*
   * Supplier Category
   */

  @Value("${validation.supplierCategory.alreadyExist}")
  private String supplierCategoryAlreadyExist;

  @Value("${validation.supplierCategory.notExist}")
  private String supplierCategoryNotExit;

  @Value("${supplierCategoryDto.category.null")
  private String categoryIsNull;

  @Value("${supplierCategoryDto.category.empty")
  private String categoryIsEmpty;

  public String getSupplierCategoryAlreadyExist() {
    return supplierCategoryAlreadyExist;
  }

  public void setSupplierCategoryAlreadyExist(String supplierCategoryAlreadyExist) {
    this.supplierCategoryAlreadyExist = supplierCategoryAlreadyExist;
  }

  public String getSupplierCategoryNotExit() {
    return supplierCategoryNotExit;
  }

  public void setSupplierCategoryNotExit(String supplierCategoryNotExit) {
    this.supplierCategoryNotExit = supplierCategoryNotExit;
  }

  public String getCategoryIsNull() {
    return categoryIsNull;
  }

  public void setCategoryIsNull(String categoryIsNull) {
    this.categoryIsNull = categoryIsNull;
  }

  public String getCategoryIsEmpty() {
    return categoryIsEmpty;
  }

  public void setCategoryIsEmpty(String categoryIsEmpty) {
    this.categoryIsEmpty = categoryIsEmpty;
  }

  /*
   * Supplier
   */

  @Value("${validation.supplier.alreadyExist}")
  private String supplierAlreadyExist;

  @Value("${validation.supplier.notExist}")
  private String supplierNotExit;

  @Value("${supplierDto.name.null")
  private String supplierNameIsNull;

  @Value("${supplierDto.name.empty")
  private String supplierNameIsEmpty;

  @Value("${supplierDto.email.null")
  private String emailIsNull;

  @Value("${supplierDto.email.empty")
  private String emailIsEmpty;
  @Value("${supplierDto.phoneNumber.null")
  private String phoneNumberIsNull;

  @Value("${supplierDto.phoneNumber.empty")
  private String phoneNumberIsEmpty;

  public String getSupplierAlreadyExist() {
    return supplierAlreadyExist;
  }

  public void setSupplierAlreadyExist(String supplierAlreadyExist) {
    this.supplierAlreadyExist = supplierAlreadyExist;
  }

  public String getSupplierNotExit() {
    return supplierNotExit;
  }

  public void setSupplierNotExit(String supplierNotExit) {
    this.supplierNotExit = supplierNotExit;
  }

  public String getSupplierNameIsNull() {
    return supplierNameIsNull;
  }

  public void setSupplierNameIsNull(String supplierNameIsNull) {
    this.supplierNameIsNull = supplierNameIsNull;
  }

  public String getSupplierNameIsEmpty() {
    return supplierNameIsEmpty;
  }

  public void setSupplierNameIsEmpty(String supplierNameIsEmpty) {
    this.supplierNameIsEmpty = supplierNameIsEmpty;
  }

  public String getEmailIsNull() {
    return emailIsNull;
  }

  public void setEmailIsNull(String emailIsNull) {
    this.emailIsNull = emailIsNull;
  }

  public String getEmailIsEmpty() {
    return emailIsEmpty;
  }

  public void setEmailIsEmpty(String emailIsEmpty) {
    this.emailIsEmpty = emailIsEmpty;
  }

  public String getPhoneNumberIsNull() {
    return phoneNumberIsNull;
  }

  public void setPhoneNumberIsNull(String phoneNumberIsNull) {
    this.phoneNumberIsNull = phoneNumberIsNull;
  }

  public String getPhoneNumberIsEmpty() {
    return phoneNumberIsEmpty;
  }

  public void setPhoneNumberIsEmpty(String phoneNumberIsEmpty) {
    this.phoneNumberIsEmpty = phoneNumberIsEmpty;
  }
  
}
