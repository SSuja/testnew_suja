
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

  public void setPlantIdIsNull(String plantIdIsNull) {
    this.plantIdIsNull = plantIdIsNull;
  }

  // for designation
  @Value("${validation.designation.notExists}")
  private String designationNotExist;

  @Value("${validation.designation.alreadyExist}")
  private String designationAlreadyExist;

  @Value("${designationDto.name.null")
  private String designationNameIsNull;

  @Value("${designationDto.name.empty")
  private String designationNameIsEmpty;

  @Value("${validation.designationName.alreadyExist}")
  private String designationNameAlreadyExist;


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


  public String getPlantIdIsNull() {
    return plantIdIsNull;
  }

  public String getDesignationNameIsNull() {
    return designationNameIsNull;
  }

  public void setDesignationNameIsNull(String designationNameIsNull) {
    this.designationNameIsNull = designationNameIsNull;
  }

  public String getDesignationNameIsEmpty() {
    return designationNameIsEmpty;
  }

  public void setDesignationNameIsEmpty(String designationNameIsEmpty) {
    this.designationNameIsEmpty = designationNameIsEmpty;
  }

  public String getDesignationNameAlreadyExist() {
    return designationNameAlreadyExist;
  }

  public void setDesignationNameAlreadyExist(String designationNameAlreadyExist) {
    this.designationNameAlreadyExist = designationNameAlreadyExist;
  }

  /*
   * Supplier
   */


  @Value("${validation.supplier.alreadyExist}")
  private String supplierAlreadyExist;

  @Value("${validation.supplier.notExist}")
  private String supplierNotExit;

  @Value("${supplierRequestDto.name.null}")
  private String supplierNameIsNull;

  @Value("${supplierRequestDto.name.empty}")
  private String supplierNameIsEmpty;

  @Value("${supplierRequestDto.email.null}")
  private String emailIsNull;

  @Value("${supplierRequestDto.email.empty}")
  private String emailIsEmpty;
  
  @Value("${supplierRequestDto.phoneNumber.null}")
  private String phoneNumberIsNull;
  
  @Value("${supplierRequestDto.phoneNumber.empty}")
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

  public void setSupplierNotExit(String supplierNotExit) {
    this.supplierNotExit = supplierNotExit;
  }

  public String getCategoryIsEmpty() {
    return categoryIsEmpty;
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
//
//  public String getSuppilerCategoryIdIsNull() {
//    return suppilerCategoryIdIsNull;
//  }
//
//  public void setSuppilerCategoryIdIsNull(String suppilerCategoryIdIsNull) {
//    this.suppilerCategoryIdIsNull = suppilerCategoryIdIsNull;
//  }
//
//  public String getSuppilerCategoryIdIsEmpty() {
//    return suppilerCategoryIdIsEmpty;
//  }
//
//  public void setSuppilerCategoryIdIsEmpty(String suppilerCategoryIdIsEmpty) {
//    this.suppilerCategoryIdIsEmpty = suppilerCategoryIdIsEmpty;
//  }

  // for employee
  @Value("${validation.employee.notExists}")

  private String employeeNotExist;

  @Value("${validation.employee.alreadyExist}")
  private String employeeAlreadyExist;

  @Value("${employeeDto.firstName.null")
  private String employeeFirstNameIsNull;

  @Value("${employeeDto.firstName.empty")
  private String employeeFirstNameIsEmpty;

  public String getEmployeeNotExist() {
    return employeeNotExist;
  }

  public void setEmployeeNotExist(String employeeNotExist) {
    this.employeeNotExist = employeeNotExist;
  }

  public String getEmployeeAlreadyExist() {
    return employeeAlreadyExist;
  }

  public void setEmployeeAlreadyExist(String employeeAlreadyExist) {
    this.employeeAlreadyExist = employeeAlreadyExist;
  }

  public String getEmployeeFirstNameIsNull() {
    return employeeFirstNameIsNull;
  }

  public void setEmployeeFirstNameIsNull(String employeeFirstNameIsNull) {
    this.employeeFirstNameIsNull = employeeFirstNameIsNull;
  }

  public String getEmployeeFirstNameIsEmpty() {
    return employeeFirstNameIsEmpty;
  }

  public void setEmployeeFirstNameIsEmpty(String employeeFirstNameIsEmpty) {
    this.employeeFirstNameIsEmpty = employeeFirstNameIsEmpty;
  }

  // for parameter
  @Value("${validation.parameter.notExists}")
  private String parameterNotExist;

  @Value("${validation.parameter.alreadyExist}")
  private String parameterAlreadyExist;
  

  @Value("${parameterDto.abbreviation.null}")
  private String abbreviationIsNull;
  
  @Value("${parameterDto.abbreviation.empty}")
  private String abbreviationIsempty;
  
  @Value("${parameterDto.name.null}")
  private String nameIsNull;
 

  public String getAbbreviationIsNull() {
    return abbreviationIsNull;
  }

  public void setAbbreviationIsNull(String abbreviationIsNull) {
    this.abbreviationIsNull = abbreviationIsNull;
  }

  public String getAbbreviationIsempty() {
    return abbreviationIsempty;
  }

  public void setAbbreviationIsempty(String abbreviationIsempty) {
    this.abbreviationIsempty = abbreviationIsempty;
  }

  public String getNameIsNull() {
    return nameIsNull;
  }

  public void setNameIsNull(String nameIsNull) {
    this.nameIsNull = nameIsNull;
  }

  public String getNameIsempty() {
    return nameIsempty;
  }

  public void setNameIsempty(String nameIsempty) {
    this.nameIsempty = nameIsempty;
  }

  public void setCategoryIsEmpty(String categoryIsEmpty) {
    this.categoryIsEmpty = categoryIsEmpty;
  }

  @Value("${parameterDto.name.empty}")
  private String nameIsempty;


  public String getParameterNotExist() {
    return parameterNotExist;
  }

  public void setParameterNotExist(String parameterNotExist) {
    this.parameterNotExist = parameterNotExist;
  }

  public String getParameterAlreadyExist() {
    return parameterAlreadyExist;
  }

  public void setParameterAlreadyExist(String parameterAlreadyExist) {
    this.parameterAlreadyExist = parameterAlreadyExist;
  }

}
