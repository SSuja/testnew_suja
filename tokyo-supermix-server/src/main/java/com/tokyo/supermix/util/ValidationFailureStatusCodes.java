
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

  public String getDesignationNotExist() {
    return designationNotExist;
  }

  @Value("${designationDto.name.empty")
  private String designationNameIsEmpty;

  @Value("${validation.designationName.alreadyExist}")
  private String designationNameAlreadyExist;

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

  public void setSupplierAlreadyExist(String supplierAlreadyExist) {
    this.supplierAlreadyExist = supplierAlreadyExist;
  }

  public String getSupplierAlreadyExist() {
    return supplierAlreadyExist;
  }

  @Value("${supplierRequestDto.email.empty}")
  private String emailIsEmpty;

  @Value("${supplierRequestDto.phoneNumber.null}")
  private String phoneNumberIsNull;

  @Value("${supplierRequestDto.phoneNumber.empty}")
  private String phoneNumberIsEmpty;

  public String getSupplierNotExit() {
    return supplierNotExit;
  }

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

  /*
   * MaterialSubCategory
   */

  public String getMaterialSubCategoryNameIsNull() {
    return materialSubCategoryNameIsNull;
  }

  public void setMaterialSubCategoryNameIsNull(String materialSubCategoryNameIsNull) {
    this.materialSubCategoryNameIsNull = materialSubCategoryNameIsNull;
  }

  public String getMaterialSubCategoryNameIsEmpty() {
    return materialSubCategoryNameIsEmpty;
  }

  public void setMaterialSubCategoryNameIsEmpty(String materialSubCategoryNameIsEmpty) {
    this.materialSubCategoryNameIsEmpty = materialSubCategoryNameIsEmpty;
  }

  public String getMaterialSubCategoryNotExist() {
    return materialSubCategoryNotExist;
  }

  public void setMaterialSubCategoryNotExist(String materialSubCategoryNotExist) {
    this.materialSubCategoryNotExist = materialSubCategoryNotExist;
  }

  public String getMaterialSubCategoryAlreadyExist() {
    return materialSubCategoryAlreadyExist;
  }

  public void setMaterialSubCategoryAlreadyExist(String materialSubCategoryAlreadyExist) {
    this.materialSubCategoryAlreadyExist = materialSubCategoryAlreadyExist;
  }

  // for customer
  @Value("${validation.customer.alreadyExist}")
  private String customerAlreadyExist;

  @Value("${customerDto.name.null")
  private String customerNameIsNull;

  @Value("${customerDto.name.empty")
  private String customerNameIsEmpty;

  @Value("${customerDto.phoneNumber.null}")
  private String customerPhoneNumberIsnull;

  @Value("${customerDto.email.null}")
  private String customerEmailIsnull;

  @Value("${validation.customer.notExists}")
  private String customerNotExist;

  @Value("${customerDto.email.empty}")
  private String customerEmailIsEmpty;

  public String getCustomerEmailIsEmpty() {
    return customerEmailIsEmpty;
  }

  public void setCustomerEmailIsEmpty(String customerEmailIsEmpty) {
    this.customerEmailIsEmpty = customerEmailIsEmpty;
  }

  public String getCustomerNameIsNull() {
    return customerNameIsNull;
  }

  public void setCustomerNameIsNull(String customerNameIsNull) {
    this.customerNameIsNull = customerNameIsNull;
  }

  public String getCustomerNameIsEmpty() {
    return customerNameIsEmpty;
  }

  public void setCustomerNameIsEmpty(String customerNameIsEmpty) {
    this.customerNameIsEmpty = customerNameIsEmpty;
  }

  public String getCustomerPhoneNumberIsnull() {
    return customerPhoneNumberIsnull;
  }

  public void setCustomerPhoneNumberIsnull(String customerPhoneNumberIsnull) {
    this.customerPhoneNumberIsnull = customerPhoneNumberIsnull;
  }

  public String getCustomerEmailIsnull() {
    return customerEmailIsnull;
  }

  public void setCustomerEmailIsnull(String customerEmailIsnull) {
    this.customerEmailIsnull = customerEmailIsnull;
  }

  public String getCustomerNotExist() {
    return customerNotExist;
  }

  public void setCustomerNotExist(String customerNotExist) {
    this.customerNotExist = customerNotExist;
  }

  // for employee
  @Value("${validation.employee.notExists}")

  private String employeeNotExist;

  @Value("${validation.supplierCategory.alreadyExist}")
  private String supplierCategoryAlreadyExist;
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

  // unit
  @Value("${validation.unit.notExists}")

  private String unitNotExist;

  @Value("${validation.unit.alreadyExist}")
  private String unitAlreadyExist;

  @Value("${unitDto.unit.null")
  private String unitIsNull;

  @Value("${unitDto.unit.empty")
  private String unitIsEmpty;

  @Value("${unitDto.unit.specialCharacter}")
  private String unitIsSpecialCharacter;

  public String getUnitNotExist() {
    return unitNotExist;
  }

  public void setUnitNotExist(String unitNotExist) {
    this.unitNotExist = unitNotExist;
  }

  public String getUnitAlreadyExist() {
    return unitAlreadyExist;
  }

  public void setUnitAlreadyExist(String unitAlreadyExist) {
    this.unitAlreadyExist = unitAlreadyExist;
  }

  public String getUnitIsNull() {
    return unitIsNull;
  }

  public void setUnitIsNull(String unitIsNull) {
    this.unitIsNull = unitIsNull;
  }

  public String getUnitIsEmpty() {
    return unitIsEmpty;
  }

  public void setUnitIsEmpty(String unitIsEmpty) {
    this.unitIsEmpty = unitIsEmpty;
  }

  public String getUnitIsSpecialCharacter() {
    return unitIsSpecialCharacter;
  }

  public void setUnitIsSpecialCharacter(String unitIsSpecialCharacter) {
    this.unitIsSpecialCharacter = unitIsSpecialCharacter;
  }

  public void setPhoneNumberIsEmpty(String phoneNumberIsEmpty) {
    this.phoneNumberIsEmpty = phoneNumberIsEmpty;
  }

  public void setCategoryIsEmpty(String categoryIsEmpty) {
    this.categoryIsEmpty = categoryIsEmpty;
  }

  /*
   * MaterialSubCategory
   */
  @Value("${validation.materialSubCategory.notExists}")
  private String materialSubCategoryNotExist;
  @Value("${validation.materialSubCategory.alreadyExist}")
  private String materialSubCategoryAlreadyExist;
  @Value("${materialSubCategoryRequestDto.name.null}")
  private String materialSubCategoryNameIsNull;
  @Value("${materialSubCategoryRequestDto.name.empty}")
  private String materialSubCategoryNameIsEmpty;

  public String getCustomerAlreadyExist() {
    return customerAlreadyExist;
  }

  public void setCustomerAlreadyExist(String customerAlreadyExist) {
    this.customerAlreadyExist = customerAlreadyExist;
  }

  // for PlantEquipment

  @Value("${validation.plantEquipment.alreadyExist}")
  private String plantEquipmentAlreadyExist;

  @Value("${validation.plantEquipment.notExist}")
  private String plantEquipmentNotExist;

  @Value("${plantEquipmentDto.serialNo.empty}")
  private String plantEquipmentSerialNoIsEmpty;

  @Value("${plantEquipmentDto.serialNo.null}")
  private String plantEquipmentSerialNoIsNull;

  @Value("${plantEquipmentDto.brandName.null}")
  private String plantEquipmentBrandNameIsNull;

  @Value("${plantEquipmentDto.brandName.empty}")
  private String plantEquipmentBrandNameIsEmpty;

  public String getPlantEquipmentAlreadyExist() {
    return plantEquipmentAlreadyExist;
  }

  public void setPlantEquipmentAlreadyExist(String plantEquipmentAlreadyExist) {
    this.plantEquipmentAlreadyExist = plantEquipmentAlreadyExist;
  }

  public String getPlantEquipmentNotExist() {
    return plantEquipmentNotExist;
  }

  public void setPlantEquipmentNotExist(String plantEquipmentNotExist) {
    this.plantEquipmentNotExist = plantEquipmentNotExist;
  }

  // for equipment
  @Value("${validation.equipment.notExists}")

  private String equipmentNotExist;
  @Value("${validation.equipment.alreadyExist}")
  private String equipmentAlreadyExist;

  public String getEquipmentNotExist() {
    return equipmentNotExist;
  }

  public void setEquipmentNotExist(String equipmentNotExist) {
    this.equipmentNotExist = equipmentNotExist;
  }

  public String getEquipmentAlreadyExist() {
    return equipmentAlreadyExist;
  }

  public void setEquipmentAlreadyExist(String equipmentAlreadyExist) {
    this.equipmentAlreadyExist = equipmentAlreadyExist;
  }

  /*
   * Material State
   */
  @Value("${validation.materialState.notExists}")
  private String materialStateNotExist;
  @Value("${validation.materialState.alreadyExist}")
  private String materialStateAlreadyExist;
  @Value("${materialStateDto.materialState.null")
  private String materialStateIsNull;
  @Value("${materialStateDto.materialState.empty")
  private String materialStateIsEmpty;

  public String getMaterialStateNotExist() {
    return materialStateNotExist;
  }

  public void setMaterialStateNotExist(String materialStateNotExist) {
    this.materialStateNotExist = materialStateNotExist;
  }

  public String getMaterialStateAlreadyExist() {
    return materialStateAlreadyExist;
  }

  public void setMaterialStateAlreadyExist(String materialStateAlreadyExist) {
    this.materialStateAlreadyExist = materialStateAlreadyExist;
  }

  public String getMaterialStateIsNull() {
    return materialStateIsNull;
  }

  public void setMaterialStateIsNull(String materialStateIsNull) {
    this.materialStateIsNull = materialStateIsNull;
  }

  public String getMaterialStateIsEmpty() {
    return materialStateIsEmpty;
  }

  public void setMaterialStateIsEmpty(String materialStateIsEmpty) {
    this.materialStateIsEmpty = materialStateIsEmpty;
  }

  /*
   * Raw Material
   */
  @Value("${validation.rawMaterial.notExists}")
  private String rawMaterialNotExist;
  @Value("${validation.rawMaterial.alreadyExist}")
  private String rawMaterialAlreadyExist;
  @Value("${rawMaterialRequestDto.name.null")
  private String rawMaterialNameIsNull;
  @Value("${rawMaterialRequestDto.name.empty")
  private String rawMaterialNameIsEmpty;

  public String getRawMaterialNotExist() {
    return rawMaterialNotExist;
  }

  public void setRawMaterialNotExist(String rawMaterialNotExist) {
    this.rawMaterialNotExist = rawMaterialNotExist;
  }

  public String getRawMaterialAlreadyExist() {
    return rawMaterialAlreadyExist;
  }

  public void setRawMaterialAlreadyExist(String rawMaterialAlreadyExist) {
    this.rawMaterialAlreadyExist = rawMaterialAlreadyExist;
  }

  public String getRawMaterialNameIsNull() {
    return rawMaterialNameIsNull;
  }

  public void setRawMaterialNameIsNull(String rawMaterialNameIsNull) {
    this.rawMaterialNameIsNull = rawMaterialNameIsNull;
  }

  public String getRawMaterialNameIsEmpty() {
    return rawMaterialNameIsEmpty;
  }

  public void setRawMaterialNameIsEmpty(String rawMaterialNameIsEmpty) {
    this.rawMaterialNameIsEmpty = rawMaterialNameIsEmpty;
  }

  public String getMaterialCategoryNotExist() {
    return MaterialCategoryNotExist;
  }

  public void setMaterialCategoryNotExist(String materialCategoryNotExist) {
    MaterialCategoryNotExist = materialCategoryNotExist;
  }

  public String getMaterialCategoryAlreadyExist() {
    return MaterialCategoryAlreadyExist;
  }

  public void setMaterialCategoryAlreadyExist(String materialCategoryAlreadyExist) {
    MaterialCategoryAlreadyExist = materialCategoryAlreadyExist;
  }

  /*
   * Test
   */
  @Value("${validation.test.notExists}")
  private String testNotExist;
  @Value("${validation.test.alreadyExist}")
  private String testAlreadyExist;
  @Value("${testRequestDto.name.null")
  private String testNameIsNull;
  @Value("${testRequestDto.name.empty")
  private String testNameIsEmpty;

  public String getTestNotExist() {
    return testNotExist;
  }

  public void setTestNotExist(String testNotExist) {
    this.testNotExist = testNotExist;
  }

  public String getTestAlreadyExist() {
    return testAlreadyExist;
  }

  public void setTestAlreadyExist(String testAlreadyExist) {
    this.testAlreadyExist = testAlreadyExist;
  }

  public String getTestNameIsNull() {
    return testNameIsNull;
  }

  public void setTestNameIsNull(String testNameIsNull) {
    this.testNameIsNull = testNameIsNull;
  }

  public String getTestNameIsEmpty() {
    return testNameIsEmpty;
  }

  public void setTestNameIsEmpty(String testNameIsEmpty) {
    this.testNameIsEmpty = testNameIsEmpty;
  }

  public String getPlantEquipmentSerialNoIsEmpty() {
    return plantEquipmentSerialNoIsEmpty;
  }

  public void setPlantEquipmentSerialNoIsEmpty(String plantEquipmentSerialNoIsEmpty) {
    this.plantEquipmentSerialNoIsEmpty = plantEquipmentSerialNoIsEmpty;
  }

  public String getPlantEquipmentSerialNoIsNull() {
    return plantEquipmentSerialNoIsNull;
  }

  public void setPlantEquipmentSerialNoIsNull(String plantEquipmentSerialNoIsNull) {
    this.plantEquipmentSerialNoIsNull = plantEquipmentSerialNoIsNull;
  }

  public String getPlantEquipmentBrandNameIsNull() {
    return plantEquipmentBrandNameIsNull;
  }

  public void setPlantEquipmentBrandNameIsNull(String plantEquipmentBrandNameIsNull) {
    this.plantEquipmentBrandNameIsNull = plantEquipmentBrandNameIsNull;
  }

  public String getPlantEquipmentBrandNameIsEmpty() {
    return plantEquipmentBrandNameIsEmpty;
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
  // public String getSuppilerCategoryIdIsNull() {
  // return suppilerCategoryIdIsNull;
  // }
  //
  // public void setSuppilerCategoryIdIsNull(String suppilerCategoryIdIsNull) {
  // this.suppilerCategoryIdIsNull = suppilerCategoryIdIsNull;
  // }
  //
  // public String getSuppilerCategoryIdIsEmpty() {
  // return suppilerCategoryIdIsEmpty;
  // }
  //
  // public void setSuppilerCategoryIdIsEmpty(String suppilerCategoryIdIsEmpty) {
  // this.suppilerCategoryIdIsEmpty = suppilerCategoryIdIsEmpty;
  // }

  public void setPlantEquipmentBrandNameIsEmpty(String plantEquipmentBrandNameIsEmpty) {
    this.plantEquipmentBrandNameIsEmpty = plantEquipmentBrandNameIsEmpty;
  }

  // for plantEquipmentCalibration
  @Value("${validation.plantEquipmentCalibration.notExists}")
  private String plantEquipmentCalibrationNotExist;
  @Value("${validation.PlantEquipmentCalibrationRequestDto.userId.null}")
  private String PlantEquipmentCalibrationRequestDtoUserIdIsNull;
  @Value("${validation.PlantEquipmentCalibrationRequestDto.supplierId.null}")
  private String PlantEquipmentCalibrationRequestDtoSupplierIdIsNull;

  public String getPlantEquipmentCalibrationRequestDtoUserIdIsNull() {
    return PlantEquipmentCalibrationRequestDtoUserIdIsNull;
  }

  public void setPlantEquipmentCalibrationRequestDtoUserIdIsNull(
      String plantEquipmentCalibrationRequestDtoUserIdIsNull) {
    PlantEquipmentCalibrationRequestDtoUserIdIsNull =
        plantEquipmentCalibrationRequestDtoUserIdIsNull;
  }

  public String getPlantEquipmentCalibrationNotExist() {
    return plantEquipmentCalibrationNotExist;
  }

  public void setPlantEquipmentCalibrationNotExist(String plantEquipmentCalibrationNotExist) {
    this.plantEquipmentCalibrationNotExist = plantEquipmentCalibrationNotExist;
  }

  public String getPlantEquipmentCalibrationRequestDtoSupplierIdIsNull() {
    return PlantEquipmentCalibrationRequestDtoSupplierIdIsNull;
  }

  public void setPlantEquipmentCalibrationRequestDtoSupplierIdIsNull(
      String plantEquipmentCalibrationRequestDtoSupplierIdIsNull) {
    PlantEquipmentCalibrationRequestDtoSupplierIdIsNull =
        plantEquipmentCalibrationRequestDtoSupplierIdIsNull;
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

  @Value("${parameterDto.name.empty}")
  private String nameIsempty;

  // for equation
  @Value("${validation.equation.notExists}")
  private String equationNotExist;
  @Value("${validation.equation.alreadyExist}")
  private String equationAlreadyExist;

  public String getEquationNotExist() {
    return equationNotExist;
  }

  public void setEquationNotExist(String equationNotExist) {
    this.equationNotExist = equationNotExist;
  }

  public String getEquationAlreadyExist() {
    return equationAlreadyExist;
  }

  public void setEquationAlreadyExist(String equationAlreadyExist) {
    this.equationAlreadyExist = equationAlreadyExist;
  }

  // For Material Category
  @Value("${validation.materialCategory.notExists}")
  private String MaterialCategoryNotExist;
  @Value("${validation.materialCategory.alreadyExist}")
  private String MaterialCategoryAlreadyExist;

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

  // Pour
  @Value("${validation.Pour.AlreadyExist}")
  private String PourAlreadyExist;
  @Value("${validation.Pour.NotExist}")
  private String PourNotExist;

  public String getPourAlreadyExist() {
    return PourAlreadyExist;
  }

  public void setPourAlreadyExist(String pourAlreadyExist) {
    PourAlreadyExist = pourAlreadyExist;
  }

  public String getPourNotExist() {
    return PourNotExist;
  }

  public void setPourNotExist(String pourNotExist) {
    PourNotExist = pourNotExist;
  }

  // For TestTypes
  @Value("${validation.testType.notExists}")
  private String testTypeNotExist;
  @Value("${validation.testType.alreadyExists}")
  private String testTypealreadyExists;
  @Value("${testTypeDto.type.null}")
  private String testTypeIsNull;
  @Value("${estTypeDto.type.empty}")
  private String testTypeIsEmpty;

  public String getTestTypeNotExist() {
    return testTypeNotExist;
  }

  public void setTestTypeNotExist(String testTypeNotExist) {
    this.testTypeNotExist = testTypeNotExist;
  }

  public String getTestTypealreadyExists() {
    return testTypealreadyExists;
  }

  public void setTestTypealreadyExists(String testTypealreadyExists) {
    this.testTypealreadyExists = testTypealreadyExists;
  }

  @Value("${validation.mixDesign.notExist}")
  private String mixDesignNotExist;

  @Value("${validation.mixDesign.alreadyExist}")
  private String mixDesignAlreadyExist;

  @Value("${MixDesignRequestDto.grade.null}")
  private String gradenotnull;

  @Value("${MixDesignRequestDto.grade.empty}")
  private String gradenotempty;

  @Value("${MixDesignRequestDto.targetStrength.null}")
  private String targetStrengthnotnull;

  @Value("${MixDesignRequestDto.grade.empty}")
  private String targetStrengthnotempty;

  @Value("${MixDesignRequestDto.waterCementRatio.null}")
  private String waterCementRationotnull;

  @Value("${MixDesignRequestDto.waterCementRatio.empty}")
  private String waterCementRationotempty;

  @Value("${MixDesignRequestDto.waterBinderRatio.null}")
  private String waterBinderRationotnull;

  @Value("${MixDesignRequestDto.waterBinderRatio.empty}")
  private String waterBinderRationotempty;

  @Value("${MixDesignRequestDto.date.null}")
  private String datenotnull;



  public String getGradenotnull() {
    return gradenotnull;
  }

  public void setGradenotnull(String gradenotnull) {
    this.gradenotnull = gradenotnull;
  }

  public String getGradenotempty() {
    return gradenotempty;
  }

  public void setGradenotempty(String gradenotempty) {
    this.gradenotempty = gradenotempty;
  }

  public String getMixDesignAlreadyExist() {
    return mixDesignAlreadyExist;
  }

  public void setMixDesignAlreadyExist(String mixDesignAlreadyExist) {
    this.mixDesignAlreadyExist = mixDesignAlreadyExist;
  }

  public String getMixDesignNotExist() {
    return mixDesignNotExist;
  }

  public void setMixDesignNotExist(String mixDesignNotExist) {
    this.mixDesignNotExist = mixDesignNotExist;
  }

  public String getTestTypeIsNull() {
    return testTypeIsNull;
  }

  public void setTestTypeIsNull(String testTypeIsNull) {
    this.testTypeIsNull = testTypeIsNull;
  }

  public String getTestTypeIsEmpty() {
    return testTypeIsEmpty;
  }

  public void setTestTypeIsEmpty(String testTypeIsEmpty) {
    this.testTypeIsEmpty = testTypeIsEmpty;
  }


  /*
   * IncomingSample
   */

  @Value("${validation.incomingSample.notExists}")
  private String incomingSampleNotExist;

  @Value("${validation.incomingSample.alreadyExist}")
  private String incomingSampleAlreadyExist;

  public String getIncomingSampleNotExist() {
    return incomingSampleNotExist;
  }

  public void setIncomingSampleNotExist(String incomingSampleNotExist) {
    this.incomingSampleNotExist = incomingSampleNotExist;
  }

  public String getIncomingSampleAlreadyExist() {
    return incomingSampleAlreadyExist;
  }

  public void setIncomingSampleAlreadyExist(String incomingSampleAlreadyExist) {
    this.incomingSampleAlreadyExist = incomingSampleAlreadyExist;
  }

  // for project
  @Value("${validation.project.notExists}")
  private String projectNotExist;

  public String getProjectNotExist() {
    return projectNotExist;
  }

  public void setProjectNotExist(String projectNotExist) {
    this.projectNotExist = projectNotExist;
  }

  public String getProjectAlreadyExist() {
    return projectAlreadyExist;
  }

  public void setProjectAlreadyExist(String projectAlreadyExist) {
    this.projectAlreadyExist = projectAlreadyExist;
  }

  @Value("${validation.project.alreadyExist}")
  private String projectAlreadyExist;

  @Value("${validation.testParameter.notExists}")
  private String testParameterNotExist;

  @Value("${validation.testParameter.alreadyExist}")
  private String testParameterAlreadyExist;

  public String getTestParameterNotExist() {
    return testParameterNotExist;
  }

  public void setTestParameterNotExist(String testParameterExist) {
    this.testParameterNotExist = testParameterExist;
  }

  public String getTestParameterAlreadyExist() {
    return testParameterAlreadyExist;
  }

  public void setTestParameterAlreadyExist(String testParameterAlreadyExist) {
    this.testParameterAlreadyExist = testParameterAlreadyExist;
  }

}
