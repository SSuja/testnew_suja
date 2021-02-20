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

  @Value("${supplierRequestDto.email.alreadyExist}")
  private String emailAlreadyExists;

  @Value("${supplierRequestDto.phoneNumber.alreadyExist}")
  private String phoneNumberAlreadyExists;

  public String getEmailAlreadyExists() {
    return emailAlreadyExists;
  }

  public void setEmailAlreadyExists(String emailAlreadyExists) {
    this.emailAlreadyExists = emailAlreadyExists;
  }

  public void setSupplierAlreadyExist(String supplierAlreadyExist) {
    this.supplierAlreadyExist = supplierAlreadyExist;
  }

  public String getSupplierAlreadyExist() {
    return supplierAlreadyExist;
  }

  public String getPhoneNumberAlreadyExists() {
    return phoneNumberAlreadyExists;
  }

  public void setPhoneNumberAlreadyExists(String phoneNumberAlreadyExists) {
    this.phoneNumberAlreadyExists = phoneNumberAlreadyExists;
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
  @Value("${validation.email.alreadyExist}")
  private String emailAlreadyExist;
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

  public String getEmailAlreadyExist() {
    return emailAlreadyExists;
  }

  public void setEmailAlreadyExist(String emailAlreadyExist) {
    this.emailAlreadyExists = emailAlreadyExist;
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
  @Value("${validation.employee.employeeIsEnableIsTrue}")
  private String employeeIsEnableIsTrue;

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

  public String getEmployeeIsEnableIsTrue() {
    return employeeIsEnableIsTrue;
  }

  public void setEmployeeIsEnableIsTrue(String employeeIsEnableIsTrue) {
    this.employeeIsEnableIsTrue = employeeIsEnableIsTrue;
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
  @Value("${validation.prefix.alreadyExist}")
  private String prefixAlreadyExist;
  @Value("${validation.rawMaterial.alreadyDepended}")
  private String rawMaterialAlreadyDepended;
  @Value("${validation.subMaterialCategory.alreadyDepended}")
  private String SubMaterialCategoryAlreadyDepended;
  @Value("${validation.materialCategory.alreadyDepended}")
  private String MaterialCategoryAlreadyDepended;

  public String getSubMaterialCategoryAlreadyDepended() {
    return SubMaterialCategoryAlreadyDepended;
  }

  public void setSubMaterialCategoryAlreadyDepended(String subMaterialCategoryAlreadyDepended) {
    SubMaterialCategoryAlreadyDepended = subMaterialCategoryAlreadyDepended;
  }

  public String getMaterialCategoryAlreadyDepended() {
    return MaterialCategoryAlreadyDepended;
  }

  public void setMaterialCategoryAlreadyDepended(String materialCategoryAlreadyDepended) {
    MaterialCategoryAlreadyDepended = materialCategoryAlreadyDepended;
  }

  public String getRawMaterialAlreadyDepended() {
    return rawMaterialAlreadyDepended;
  }

  public void setRawMaterialAlreadyDepended(String rawMaterialAlreadyDepended) {
    this.rawMaterialAlreadyDepended = rawMaterialAlreadyDepended;
  }

  public String getPrefixAlreadyExist() {
    return prefixAlreadyExist;
  }

  public void setPrefixAlreadyExist(String prefixAlreadyExist) {
    this.prefixAlreadyExist = prefixAlreadyExist;
  }

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
  @Value("${validation.testConfigure.notExists}")
  private String testConfigureNotExist;
  @Value("${validation.testConfigure.alreadyExist}")
  private String testConfigureAlreadyExist;
  @Value("${testConfigureRequestDto.name.null")
  private String testNameIsNull;
  @Value("${testConfigureRequestDto.name.empty")
  private String testNameIsEmpty;
  @Value("${validation.testConfigure.alreadyDepended}")
  private String testConfigureAlreadyDepended;

  public String getTestConfigureAlreadyDepended() {
    return testConfigureAlreadyDepended;
  }

  public void setTestConfigureAlreadyDepended(String testConfigureAlreadyDepended) {
    this.testConfigureAlreadyDepended = testConfigureAlreadyDepended;
  }

  public String getTestConfigureNotExist() {
    return testConfigureNotExist;
  }

  public void setTestConfigureNotExist(String testConfigureNotExist) {
    this.testConfigureNotExist = testConfigureNotExist;
  }

  public String getTestConfigureAlreadyExist() {
    return testConfigureAlreadyExist;
  }

  public void setTestConfigureAlreadyExist(String testConfigureAlreadyExist) {
    this.testConfigureAlreadyExist = testConfigureAlreadyExist;
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

  public void setPlantEquipmentBrandNameIsEmpty(String plantEquipmentBrandNameIsEmpty) {
    this.plantEquipmentBrandNameIsEmpty = plantEquipmentBrandNameIsEmpty;
  }

  // for parameter
  @Value("${validation.parameter.notExists}")
  private String parameterNotExist;

  @Value("${validation.parameter.alreadyExist}")
  private String parameterAlreadyExist;

  @Value("${parameterDto.name.null}")
  private String nameIsNull;

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
  @Value("${validation.formula.null}")
  private String formulaIsNull;

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

  public String getFormulaIsNull() {
    return formulaIsNull;
  }

  public void setFormulaIsNull(String formulaIsNull) {
    this.formulaIsNull = formulaIsNull;
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

  // For MixDesign
  @Value("${validation.mixDesign.notExist}")
  private String mixDesignNotExist;

  @Value("${validation.mixDesign.alreadyExist}")
  private String mixDesignAlreadyExist;

  @Value("${mixDesignRequestDto.targetGrade.null}")
  private String targetGradeNotNull;

  @Value("${mixDesignRequestDto.targetSlump.null}")
  private String targetSlumpNotNull;

  @Value("${mixDesignRequestDto.date.null}")
  private String dateNotNull;

  public String getMixDesignNotExist() {
    return mixDesignNotExist;
  }

  public void setMixDesignNotExist(String mixDesignNotExist) {
    this.mixDesignNotExist = mixDesignNotExist;
  }

  public String getMixDesignAlreadyExist() {
    return mixDesignAlreadyExist;
  }

  public void setMixDesignAlreadyExist(String mixDesignAlreadyExist) {
    this.mixDesignAlreadyExist = mixDesignAlreadyExist;
  }

  public String getTargetGradeNotNull() {
    return targetGradeNotNull;
  }

  public void setTargetGradeNotNull(String targetGradeNotNull) {
    this.targetGradeNotNull = targetGradeNotNull;
  }

  public String getTargetSlumpNotNull() {
    return targetSlumpNotNull;
  }

  public void setTargetSlumpNotNull(String targetSlumpNotNull) {
    this.targetSlumpNotNull = targetSlumpNotNull;
  }

  public String getDateNotNull() {
    return dateNotNull;
  }

  public void setDateNotNull(String dateNotNull) {
    this.dateNotNull = dateNotNull;
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

  // for mixDesignProportion

  @Value("${validation.mixDesignProportion.notExist}")
  private String mixDesignProportionNotExist;

  @Value("${validation.mixDesignProportion.alreadyExist}")
  private String mixDesignProportionAlreadyExist;

  @Value("${mixDesignProportionRequestDto.quantity.null}")
  private String quantityIsNull;

  @Value("${mixDesignProportionRequestDto.quantity.empty}")
  private String quantityIsEmpty;
  @Value("${validation.mixDesignProportion.deleteValid}")
  private String mixDesignProportionDeleteValid;

  public String getMixDesignProportionDeleteValid() {
    return mixDesignProportionDeleteValid;
  }

  public void setMixDesignProportionDeleteValid(String mixDesignProportionDeleteValid) {
    this.mixDesignProportionDeleteValid = mixDesignProportionDeleteValid;
  }

  public String getMixDesignProportionNotExist() {
    return mixDesignProportionNotExist;
  }

  public void setMixDesignProportionNotExist(String mixDesignProportionNotExist) {
    this.mixDesignProportionNotExist = mixDesignProportionNotExist;
  }

  public String getMixDesignProportionAlreadyExist() {
    return mixDesignProportionAlreadyExist;
  }

  public void setMixDesignProportionAlreadyExist(String mixDesignProportionAlreadyExist) {
    this.mixDesignProportionAlreadyExist = mixDesignProportionAlreadyExist;
  }

  public String getQuantityNotNull() {
    return quantityIsNull;
  }

  /*
   * Material Test
   */
  @Value("${validation.materialTest.notExists}")
  private String materialTestNotExist;

  @Value("${validation.materialTest.alreadyExist}")
  private String materialTestAlreadyExists;

  @Value("${validation.status.notExixts}")
  private String materialTestStatusNotExists;

  @Value("${validation.status.valid}")
  private String materialTestStatusValid;

  public String getMaterialTestStatusValid() {
    return materialTestStatusValid;
  }

  public void setMaterialTestStatusValid(String materialTestStatusValid) {
    this.materialTestStatusValid = materialTestStatusValid;
  }

  public String getMaterialTestNotExist() {
    return materialTestNotExist;
  }

  public void setMaterialTestNotExist(String materialTestNotExist) {
    this.materialTestNotExist = materialTestNotExist;
  }

  public String getMaterialTestStatusNotExists() {
    return materialTestStatusNotExists;
  }

  public void setMaterialTestStatusNotExists(String materialTestStatusNotExists) {
    this.materialTestStatusNotExists = materialTestStatusNotExists;
  }

  public String getMaterialTestAlreadyExists() {
    return materialTestAlreadyExists;
  }

  public void setMaterialTestAlreadyExists(String materialTestAlreadyExists) {
    this.materialTestAlreadyExists = materialTestAlreadyExists;
  }

  public void setQuantityNotNull(String quantityNotNull) {
    this.quantityIsNull = quantityNotNull;
  }

  public String getQuantityNotEmpty() {
    return quantityIsEmpty;
  }

  public void setQuantityNotEmpty(String quantityNotEmpty) {
    this.quantityIsEmpty = quantityNotEmpty;
  }

  /*
   * ProcessSampleLoad
   */

  @Value("${validation.processSampleLoad.notExists}")
  private String processSampleLoadNotExist;

  @Value("${validation.processSampleLoad.alreadyExist}")
  private String processSampleLoadAlreadyExist;

  public String getProcessSampleLoadNotExist() {
    return processSampleLoadNotExist;
  }

  public void setProcessSampleLoadNotExist(String processSampleLoadNotExist) {
    this.processSampleLoadNotExist = processSampleLoadNotExist;
  }

  public String getProcessSampleLoadAlreadyExist() {
    return processSampleLoadAlreadyExist;
  }

  public void setProcessSampleLoadAlreadyExist(String processSampleLoadAlreadyExist) {
    this.processSampleLoadAlreadyExist = processSampleLoadAlreadyExist;
  }

  @Value("${validation.testParameter.notExists}")
  private String testParameterNotExist;

  @Value("${validation.testParameter.alreadyExist}")
  private String testParameterAlreadyExist;

  @Value("${testParameterRequestDto.abbreviation.alreadyExist}")
  private String abbreviationAlreadyExist;

  @Value("${validation.testParameter.alreadyDepended}")
  private String testParameterAlreadyDepended;

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

  public String getAbbreviationAlreadyExist() {
    return abbreviationAlreadyExist;
  }

  public void setAbbreviationAlreadyExist(String abbreviationAlreadyExist) {
    this.abbreviationAlreadyExist = abbreviationAlreadyExist;
  }

  public String getTestParameterAlreadyDepended() {
    return testParameterAlreadyDepended;
  }

  public void setTestParameterAlreadyDepended(String testParameterAlreadyDepended) {
    this.testParameterAlreadyDepended = testParameterAlreadyDepended;
  }

  // for ProcessSample
  @Value("${validation.processSample.alreadyExist}")
  private String processSampleAlreadyExist;
  @Value("${validation.processSample.notExists}")
  private String processSampleNotExist;

  public String getProcessSampleAlreadyExist() {
    return processSampleAlreadyExist;
  }

  public void setProcessSampleAlreadyExist(String processSampleAlreadyExist) {
    this.processSampleAlreadyExist = processSampleAlreadyExist;
  }

  public String getProcessSampleNotExist() {
    return processSampleNotExist;
  }

  public void setProcessSampleNotExist(String processSampleNotExist) {
    this.processSampleNotExist = processSampleNotExist;
  }

  // for finish product
  @Value("${validation.finishProduct.notExist}")
  private String finishProductNotExist;

  @Value("${validation.finishProduct.alreadyExist}")
  private String finishProductAlreadyExist;

  @Value("${finishProductRequestDto.mixDesignCode.empty}")
  private String mixDesignCodeIsEmpty;

  @Value("${finishProductRequestDto.mixDesignCode.null}")
  private String mixDesignCodeIsNull;

  @Value("${finishProductRequestDto.projectCode.empty}")
  private String projectCodeIsEmpty;

  @Value("${finishProductRequestDto.projectCode.null}")
  private String projectCodeIsNull;

  public String getQuantityIsNull() {
    return quantityIsNull;
  }

  public void setQuantityIsNull(String quantityIsNull) {
    this.quantityIsNull = quantityIsNull;
  }

  public String getQuantityIsEmpty() {
    return quantityIsEmpty;
  }

  public void setQuantityIsEmpty(String quantityIsEmpty) {
    this.quantityIsEmpty = quantityIsEmpty;
  }

  public String getFinishProductNotExist() {
    return finishProductNotExist;
  }

  public void setFinishProductNotExist(String finishProductNotExist) {
    this.finishProductNotExist = finishProductNotExist;
  }

  public String getFinishProductAlreadyExist() {
    return finishProductAlreadyExist;
  }

  public void setFinishProductAlreadyExist(String finishProductAlreadyExist) {
    this.finishProductAlreadyExist = finishProductAlreadyExist;
  }

  public String getMixDesignCodeIsEmpty() {
    return mixDesignCodeIsEmpty;
  }

  public void setMixDesignCodeIsEmpty(String mixDesignCodeIsEmpty) {
    this.mixDesignCodeIsEmpty = mixDesignCodeIsEmpty;
  }

  public String getMixDesignCodeIsNull() {
    return mixDesignCodeIsNull;
  }

  public void setMixDesignCodeIsNull(String mixDesignCodeIsNull) {
    this.mixDesignCodeIsNull = mixDesignCodeIsNull;
  }

  public String getProjectCodeIsEmpty() {
    return projectCodeIsEmpty;
  }

  public void setProjectCodeIsEmpty(String projectCodeIsEmpty) {
    this.projectCodeIsEmpty = projectCodeIsEmpty;
  }

  public String getProjectCodeIsNull() {
    return projectCodeIsNull;
  }

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

  @Value("${validation.admixtureAcceptedValue.notExists}")
  private String admixtureAcceptedValueNotExist;

  public String getAdmixtureAcceptedValueNotExist() {
    return admixtureAcceptedValueNotExist;
  }

  public void setAdmixtureAcceptedValueNotExist(String admixtureAcceptedValueNotExist) {
    this.admixtureAcceptedValueNotExist = admixtureAcceptedValueNotExist;
  }

  @Value("${validation.testId.alreadyExist}")
  private String testIdAlreadyExist;

  public String getTestIdAlreadyExist() {
    return testIdAlreadyExist;
  }

  public void setTestIdAlreadyExist(String testIdAlreadyExist) {
    this.testIdAlreadyExist = testIdAlreadyExist;
  }

  // for acceptedValue
  @Value("${validation.acceptedValue.notExists}")
  private String acceptedValueNotExist;
  @Value("${validation.acceptedValue.alreadyExists}")
  private String acceptedValueAlreadyExist;
  @Value("${validation.acceptedValue.notNull}")
  private String acceptedValueNotNull;

  public String getAcceptedValueNotNull() {
    return acceptedValueNotNull;
  }

  public void setAcceptedValueNotNull(String acceptedValueNotNull) {
    this.acceptedValueNotNull = acceptedValueNotNull;
  }

  public String getAcceptedValueAlreadyExist() {
    return acceptedValueAlreadyExist;
  }

  public void setAcceptedValueAlreadyExist(String acceptedValueAlreadyExist) {
    this.acceptedValueAlreadyExist = acceptedValueAlreadyExist;
  }

  public String getAcceptedValueNotExist() {
    return acceptedValueNotExist;
  }

  public void setAcceptedValueNotExist(String acceptedValueNotExist) {
    this.acceptedValueNotExist = acceptedValueNotExist;
  }

  @Value("${validation.materialTestTrail.alreadyExist}")
  private String materialTestTrailAlreadyExist;
  @Value("${validation.materialTestTrail.notExists}")
  private String materialTestTrailNotExist;

  public String getMaterialTestTrailAlreadyExist() {
    return materialTestTrailAlreadyExist;
  }

  public void setMaterialTestTrailAlreadyExist(String materialTestTrailAlreadyExist) {
    this.materialTestTrailAlreadyExist = materialTestTrailAlreadyExist;
  }

  public String getMaterialTestTrailNotExist() {
    return materialTestTrailNotExist;
  }

  public void setMaterialTestTrailNotExist(String materialTestTrailNotExist) {
    this.materialTestTrailNotExist = materialTestTrailNotExist;
  }

  // for plantEquipmentCalibration
  @Value("${validation.plantEquipmentCalibration.notExists}")
  private String plantEquipmentCalibrationNotExist;
  @Value("${validation.employeeId.null}")
  private String EmployeeIdIsNull;
  @Value("${validation.supplierId.null}")
  private String SupplierIdIsNull;
  @Value("${validation.dueDate.exist}")
  private String dueDateExist;

  public String getDueDateExist() {
    return dueDateExist;
  }

  public void setDueDateExist(String dueDateExist) {
    this.dueDateExist = dueDateExist;
  }

  public String getPlantEquipmentCalibrationNotExist() {
    return plantEquipmentCalibrationNotExist;
  }

  public void setPlantEquipmentCalibrationNotExist(String plantEquipmentCalibrationNotExist) {
    this.plantEquipmentCalibrationNotExist = plantEquipmentCalibrationNotExist;
  }

  public String getEmployeeIdIsNull() {
    return EmployeeIdIsNull;
  }

  public void setEmployeeIdIsNull(String employeeIdIsNull) {
    EmployeeIdIsNull = employeeIdIsNull;
  }

  public String getSupplierIdIsNull() {
    return SupplierIdIsNull;
  }

  public void setSupplierIdIsNull(String supplierIdIsNull) {
    SupplierIdIsNull = supplierIdIsNull;
  }

  // for parameterResult
  @Value("${validation.parameterResult.notExists}")
  private String parameterResultNotExist;

  public String getParameterResultNotExist() {
    return parameterResultNotExist;
  }

  public void setParameterResultNotExist(String parameterResultNotExist) {
    this.parameterResultNotExist = parameterResultNotExist;
  }

  // for finishProductSample
  @Value("${validation.finishProductSample.notExists}")
  private String finishProductSampleNotExist;
  @Value("${validation.finishProductSample.alreadyExist}")
  private String finishProductSampleAlreadyExist;
  @Value("${finishProductSampleRequestDto.finishProductCode.null}")
  private String finishProductSampleCodeIsNull;

  public String getFinishProductSampleNotExist() {
    return finishProductSampleNotExist;
  }

  public void setFinishProductSampleNotExist(String finishProductSampleNotExist) {
    this.finishProductSampleNotExist = finishProductSampleNotExist;
  }

  public String getFinishProductSampleAlreadyExist() {
    return finishProductSampleAlreadyExist;
  }

  public void setFinishProductSampleAlreadyExist(String finishProductSampleAlreadyExist) {
    this.finishProductSampleAlreadyExist = finishProductSampleAlreadyExist;
  }

  public String getFinishProductSampleCodeIsNull() {
    return finishProductSampleCodeIsNull;
  }

  public void setFinishProductSampleCodeIsNull(String finishProductSampleCodeIsNull) {
    this.finishProductSampleCodeIsNull = finishProductSampleCodeIsNull;
  }

  // For finishProductSampleIssue
  @Value("${validation.finishProductSampleIssue.notExists}")
  private String finishProductSampleIssueNotExists;

  @Value("${validation.finishProductSampleIssue.alreadyExist}")
  private String finishProductSampleIssueAlreadyExist;

  public String getFinishProductSampleIssueNotExists() {
    return finishProductSampleIssueNotExists;
  }

  public void setFinishProductSampleIssueNotExists(String finishProductSampleIssueNotExists) {
    this.finishProductSampleIssueNotExists = finishProductSampleIssueNotExists;
  }

  public String getFinishProductSampleIssueAlreadyExist() {
    return finishProductSampleIssueAlreadyExist;
  }

  public void setFinishProductSampleIssueAlreadyExist(String finishProductSampleIssueAlreadyExist) {
    this.finishProductSampleIssueAlreadyExist = finishProductSampleIssueAlreadyExist;
  }

  /*
   * Test
   */
  @Value("${validation.test.notExists}")
  private String testNotExist;
  @Value("${validation.test.alreadyExist}")
  private String testAlreadyExist;

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

  @Value("${validation.qualityParameter.notExists}")
  private String qualityParameterNotExist;
  @Value("${validation.qualityParameter.alreadyExist}")
  private String qualityParameterAlreadyExist;

  public String getQualityParameterNotExist() {
    return qualityParameterNotExist;
  }

  public void setQualityParameterNotExist(String qualityParameterNotExist) {
    this.qualityParameterNotExist = qualityParameterNotExist;
  }

  public String getQualityParameterAlreadyExist() {
    return qualityParameterAlreadyExist;
  }

  public void setQualityParameterAlreadyExist(String qualityParameterAlreadyExist) {
    this.qualityParameterAlreadyExist = qualityParameterAlreadyExist;
  }

  /*
   * Test parameter
   */
  @Value("${testParameterDto.abbreviation.null}")
  private String abbreviationIsNull;

  @Value("${testParameterDto.abbreviation.alreadyExist}")
  private String abbreviationAlreadyExit;

  @Value("${testParameterDto.value.null}")
  private String valueIsNull;

  public String getAbbreviationAlreadyExit() {
    return abbreviationAlreadyExit;
  }

  public void setAbbreviationAlreadyExit(String abbreviationAlreadyExit) {
    this.abbreviationAlreadyExit = abbreviationAlreadyExit;
  }

  public String getAbbreviationIsNull() {
    return abbreviationIsNull;
  }

  public void setAbbreviationIsNull(String abbreviationIsNull) {
    this.abbreviationIsNull = abbreviationIsNull;
  }

  public String getValueIsNull() {
    return valueIsNull;
  }

  public void setValueIsNull(String valueIsNull) {
    this.valueIsNull = valueIsNull;
  }

  /*
   * Parameter Equation
   */
  @Value("${validation.parameterEquation.notExist}")
  private String parameterEquationNotExit;
  @Value("${validation.parameterEquation.alreadyExist}")
  private String parameterEquationAlreadyExit;

  public String getParameterEquationNotExit() {
    return parameterEquationNotExit;
  }

  public void setParameterEquationNotExit(String parameterEquationNotExit) {
    this.parameterEquationNotExit = parameterEquationNotExit;
  }

  public String getParameterEquationAlreadyExit() {
    return parameterEquationAlreadyExit;
  }

  public void setParameterEquationAlreadyExit(String parameterEquationAlreadyExit) {
    this.parameterEquationAlreadyExit = parameterEquationAlreadyExit;
  }

  /*
   * Parameter Equation Element
   */
  @Value("${validation.parameterEquationElement.notExist}")
  private String parameterEquationElementNotExit;
  @Value("${validation.parameterEquationElement.alreadyExist}")
  private String parameterEquationElementAlreadyExit;

  public String getParameterEquationElementNotExit() {
    return parameterEquationElementNotExit;
  }

  public void setParameterEquationElementNotExit(String parameterEquationElementNotExit) {
    this.parameterEquationElementNotExit = parameterEquationElementNotExit;
  }

  public String getParameterEquationElementAlreadyExit() {
    return parameterEquationElementAlreadyExit;
  }

  public void setParameterEquationElementAlreadyExit(String parameterEquationElementAlreadyExit) {
    this.parameterEquationElementAlreadyExit = parameterEquationElementAlreadyExit;

  }

  /*
   * finish product test
   */
  @Value("${validation.finishProductTest.notExists}")
  private String finishProductTestNotExit;
  @Value("${validation.finishProductTest.alreadyExists}")
  private String finishProductTestAlreadyExists;

  public String getFinishProductTestAlreadyExists() {
    return finishProductTestAlreadyExists;
  }

  public void setFinishProductTestAlreadyExists(String finishProductTestAlreadyExists) {
    this.finishProductTestAlreadyExists = finishProductTestAlreadyExists;
  }

  public String getFinishProductTestNotExit() {
    return finishProductTestNotExit;
  }

  public void setFinishProductTestNotExit(String finishProductTestNotExit) {
    this.finishProductTestNotExit = finishProductTestNotExit;
  }

  public void setProjectCodeIsNull(String projectCodeIsNull) {
    this.projectCodeIsNull = projectCodeIsNull;
  }

  /*
   * Finish Product Trial
   */
  @Value("${validation.finishProductTrial.notExist}")
  private String finishProductTrialNotExit;

  public String getFinishProductTrialNotExit() {
    return finishProductTrialNotExit;
  }

  public void setFinishProductTrialNotExit(String finishProductTrialNotExit) {
    this.finishProductTrialNotExit = finishProductTrialNotExit;
  }

  @Value("${validation.emailRecipient.alreadyExist}")
  private String emailRecipientAlreadyExist;

  @Value("${validation.emailRecipient.notExist}")
  private String emailRecipientnotExist;

  public String getEmailRecipientAlreadyExist() {
    return emailRecipientAlreadyExist;
  }

  public void setEmailRecipientAlreadyExist(String emailRecipientAlreadyExist) {
    this.emailRecipientAlreadyExist = emailRecipientAlreadyExist;
  }

  public String getEmailRecipientnotExist() {
    return emailRecipientnotExist;
  }

  public void setEmailRecipientnotExist(String emailRecipientnotExist) {
    this.emailRecipientnotExist = emailRecipientnotExist;
  }

  @Value("${validation.emailNotificationDays.alreadyExist}")
  private String emailNotificationDaysAlreadyExist;

  @Value("${validation.emailNotificationDays.notExist}")
  private String emailNotificationDaysNotExist;

  public String getEmailNotificationDaysAlreadyExist() {
    return emailNotificationDaysAlreadyExist;
  }

  public void setEmailNotificationDaysAlreadyExist(String emailNotificationDaysAlreadyExist) {
    this.emailNotificationDaysAlreadyExist = emailNotificationDaysAlreadyExist;
  }

  public String getEmailNotificationDaysNotExist() {
    return emailNotificationDaysNotExist;
  }

  public void setEmailNotificationDaysNotExist(String emailNotificationDaysNotExist) {
    this.emailNotificationDaysNotExist = emailNotificationDaysNotExist;
  }

  @Value("${validation.emailGroup.alreadyExist}")
  private String emailGroupAlreadyExist;

  @Value("${validation.emailGroup.notExist}")
  private String emailGroupNotExist;

  public String getEmailGroupAlreadyExist() {
    return emailGroupAlreadyExist;
  }

  public void setEmailGroupAlreadyExist(String emailGroupAlreadyExist) {
    this.emailGroupAlreadyExist = emailGroupAlreadyExist;
  }

  public String getEmailGroupNotExist() {
    return emailGroupNotExist;
  }

  public void setEmailGroupNotExist(String emailGroupNotExist) {
    this.emailGroupNotExist = emailGroupNotExist;
  }

  @Value("${validation.emailPoints.notExists}")
  private String emailPointsNotExist;
  @Value("${validation.emailPoints.alreadyExist}")
  private String emailPointsAlreadyExist;

  public String getEmailPointsNotExist() {
    return emailPointsNotExist;
  }

  public void setEmailPointsNotExist(String emailPointsNotExist) {
    this.emailPointsNotExist = emailPointsNotExist;
  }

  /*
   * Test Equation
   */
  @Value("${validation.testEquation.notExists}")
  private String testEquationNotExist;
  @Value("${validation.testEquation.alreadyExist}")
  private String testEquationAlreadyExist;

  public String getTestEquationNotExist() {
    return testEquationNotExist;
  }

  public void setTestEquationNotExist(String testEquationNotExist) {
    this.testEquationNotExist = testEquationNotExist;
  }

  public String getTestEquationAlreadyExist() {
    return testEquationAlreadyExist;
  }

  public void setTestEquationAlreadyExist(String testEquationAlreadyExist) {
    this.testEquationAlreadyExist = testEquationAlreadyExist;
  }

  public String getEmailPointsAlreadyExist() {
    return emailPointsAlreadyExist;
  }

  public void setEmailPointsAlreadyExist(String emailPointsAlreadyExist) {
    this.emailPointsAlreadyExist = emailPointsAlreadyExist;
  }

  /*
   * Finish Product Accepted Value
   */
  @Value("${validation.finishProductAcceptedValue.notExists}")
  private String finishProductAcceptedValueNotExist;

  public String getFinishProductAcceptedValueNotExist() {
    return finishProductAcceptedValueNotExist;
  }

  public void setFinishProductAcceptedValueNotExist(String finishProductAcceptedValueNotExist) {
    this.finishProductAcceptedValueNotExist = finishProductAcceptedValueNotExist;
  }

  // SBU
  @Value("${validation.subBusinessUnit.notExists}")
  private String subBusinessUnitNotExist;

  @Value("${validation.subBusinessUnit.alreadyExist}")
  private String subBusinessUnitAlreadyExist;

  @Value("${subBusinessUnitDto.name.null}")
  private String subBusinessUnitNameIsNull;

  public String getSubBusinessUnitNotExist() {
    return subBusinessUnitNotExist;
  }

  public void setSubBusinessUnitNotExist(String subBusinessUnitNotExist) {
    this.subBusinessUnitNotExist = subBusinessUnitNotExist;
  }

  public String getSubBusinessUnitAlreadyExist() {
    return subBusinessUnitAlreadyExist;
  }

  public void setSubBusinessUnitAlreadyExist(String subBusinessUnitAlreadyExist) {
    this.subBusinessUnitAlreadyExist = subBusinessUnitAlreadyExist;
  }

  public String getSubBusinessUnitNameIsNull() {
    return subBusinessUnitNameIsNull;
  }

  public void setSubBusinessUnitNameIsNull(String subBusinessUnitNameIsNull) {
    this.subBusinessUnitNameIsNull = subBusinessUnitNameIsNull;
  }

  // ratio config
  @Value("${validation.ratioConfig.notExists}")
  private String ratioConfigNotExist;

  @Value("${validation.ratioConfig.alreadyExist}")
  private String ratioConfigAlreadyExist;

  @Value("${validation.ratioConfig.ratiParaNotExists}")
  private String ratioConfigRatioParaNotExists;

  @Value("${validation.ratioConfig.deleteValidate}")
  private String ratioConfigRatioParaDeleteValidate;

  @Value("${validation.ratioConfig.alreadyDepended}")
  private String ratioConfigAlreadyDepended;

  public String getRatioConfigAlreadyDepended() {
    return ratioConfigAlreadyDepended;
  }

  public void setRatioConfigAlreadyDepended(String ratioConfigAlreadyDepended) {
    this.ratioConfigAlreadyDepended = ratioConfigAlreadyDepended;
  }

  public String getRatioConfigRatioParaDeleteValidate() {
    return ratioConfigRatioParaDeleteValidate;
  }

  public void setRatioConfigRatioParaDeleteValidate(String ratioConfigRatioParaDeleteValidate) {
    this.ratioConfigRatioParaDeleteValidate = ratioConfigRatioParaDeleteValidate;
  }

  public String getRatioConfigNotExist() {
    return ratioConfigNotExist;
  }

  public void setRatioConfigNotExist(String ratioConfigNotExist) {
    this.ratioConfigNotExist = ratioConfigNotExist;
  }

  public String getRatioConfigAlreadyExist() {
    return ratioConfigAlreadyExist;
  }

  public void setRatioConfigAlreadyExist(String ratioConfigAlreadyExist) {
    this.ratioConfigAlreadyExist = ratioConfigAlreadyExist;
  }

  public String getRatioConfigRatioParaNotExists() {
    return ratioConfigRatioParaNotExists;
  }

  public void setRatioConfigRatioParaNotExists(String ratioConfigRatioParaNotExists) {
    this.ratioConfigRatioParaNotExists = ratioConfigRatioParaNotExists;
  }

  // ratio config parameter
  @Value("${validation.ratioConfigParameter.notExists}")
  private String ratioConfigParameterNotExist;

  @Value("${validation.ratioConfigParameter.alreadyExist}")
  private String ratioConfigParameterAlreadyExist;

  // ratio config equation
  @Value("${validation.ratioConfigEquation.notExists}")
  private String ratioConfigEquationNotExist;

  @Value("${validation.ratioConfigEquation.alreadyExist}")
  private String ratioConfigEquationAlreadyExist;

  public String getRatioConfigParameterNotExist() {
    return ratioConfigParameterNotExist;
  }

  public void setRatioConfigParameterNotExist(String ratioConfigParameterNotExist) {
    this.ratioConfigParameterNotExist = ratioConfigParameterNotExist;
  }

  public String getRatioConfigParameterAlreadyExist() {
    return ratioConfigParameterAlreadyExist;
  }

  public void setRatioConfigParameterAlreadyExist(String ratioConfigParameterAlreadyExist) {
    this.ratioConfigParameterAlreadyExist = ratioConfigParameterAlreadyExist;
  }

  public String getRatioConfigEquationNotExist() {
    return ratioConfigEquationNotExist;
  }

  public void setRatioConfigEquationNotExist(String ratioConfigEquationNotExist) {
    this.ratioConfigEquationNotExist = ratioConfigEquationNotExist;
  }

  public String getRatioConfigEquationAlreadyExist() {
    return ratioConfigEquationAlreadyExist;
  }

  public void setRatioConfigEquationAlreadyExist(String ratioConfigEquationAlreadyExist) {
    this.ratioConfigEquationAlreadyExist = ratioConfigEquationAlreadyExist;
  }

  // ratio config parameter
  @Value("${validation.mixDesignRatioConfig.notExists}")
  private String mixDesignRatioConfigNotExist;

  public String getMixDesignRatioConfigNotExist() {
    return mixDesignRatioConfigNotExist;
  }

  public void setMixDesignRatioConfigNotExist(String mixDesignRatioConfigNotExist) {
    this.mixDesignRatioConfigNotExist = mixDesignRatioConfigNotExist;
  }

  // macAddress
  @Value("${validation.macAddress.notExists}")
  private String macAddressNotExist;

  @Value("${validation.macAddress.alreadyExist}")
  private String macAddressAlreadyExist;

  @Value("${macAddressDto.macAddress.null}")
  private String macAddressIsNull;

  @Value("${macAddressDto.macAddress.empty}")
  private String macAddressIsEmpty;

  public String getMacAddressNotExist() {
    return macAddressNotExist;
  }

  public void setMacAddressNotExist(String macAddressNotExist) {
    this.macAddressNotExist = macAddressNotExist;
  }

  public String getMacAddressAlreadyExist() {
    return macAddressAlreadyExist;
  }

  public void setMacAddressAlreadyExist(String macAddressAlreadyExist) {
    this.macAddressAlreadyExist = macAddressAlreadyExist;
  }

  public String getMacAddressIsNull() {
    return macAddressIsNull;
  }

  public void setMacAddressIsNull(String macAddressIsNull) {
    this.macAddressIsNull = macAddressIsNull;
  }

  public String getMacAddressIsEmpty() {
    return macAddressIsEmpty;
  }

  public void setMacAddressIsEmpty(String macAddressIsEmpty) {
    this.macAddressIsEmpty = macAddressIsEmpty;
  }

  // uploadImages
  @Value("${validation.testId.null}")
  private String testIdNull;

  public String getTestIdNull() {
    return testIdNull;
  }

  public void setTestIdNull(String testIdNull) {
    this.testIdNull = testIdNull;
  }

  @Value("${validation.testId.notExists}")
  private String testIdNotExists;

  public String getTestIdNotExists() {
    return testIdNotExists;
  }

  public void setTestIdNotExists(String testIdNotExists) {
    this.testIdNotExists = testIdNotExists;
  }

  @Value("${uploadDto.testImage.null}")
  private String fileNull;

  public String getFileNull() {
    return fileNull;
  }

  public void setFileNull(String fileNull) {
    this.fileNull = fileNull;
  }

  @Value("${uploadDto.testImage.notExists}")
  private String imageNotExists;

  public String getImageNotExists() {
    return imageNotExists;
  }

  public void setImageNotExists(String imageNotExists) {
    this.imageNotExists = imageNotExists;
  }

  @Value("${uploadDto.image.notValidContent}")
  private String imageNotValidContent;

  public String getImageNotValidContent() {
    return imageNotValidContent;
  }

  public void setImageNotValidContent(String imageNotValidContent) {
    this.imageNotValidContent = imageNotValidContent;
  }

  // table format
  @Value("${validation.tableFormat.alreadyExist}")
  private String tableFormatAlreadyExist;

  public String getTableFormatAlreadyExist() {
    return tableFormatAlreadyExist;
  }

  public void setTableFormatAlreadyExist(String tableFormatAlreadyExist) {
    this.tableFormatAlreadyExist = tableFormatAlreadyExist;
  }

  // sample type
  @Value("${validation.rawMaterialSampleType.notExists}")
  private String rawMaterialSampleTypeNotExists;

  public String getRawMaterialSampleTypeNotExists() {
    return rawMaterialSampleTypeNotExists;
  }

  public void setRawMaterialSampleTypeNotExists(String rawMaterialSampleTypeNotExists) {
    this.rawMaterialSampleTypeNotExists = rawMaterialSampleTypeNotExists;
  }

  /*
   * Multi Result Formula
   */
  @Value("${validation.multiResultFormula.notExists}")
  private String multiResultFormulaNotExist;

  public String getMultiResultFormulaNotExist() {
    return multiResultFormulaNotExist;
  }

  public void setMultiResultFormulaNotExist(String multiResultFormulaNotExist) {
    this.multiResultFormulaNotExist = multiResultFormulaNotExist;
  }

  @Value("${validation.mixDesignProportion.hasZero}")
  private String mixDesignProportionHasZeroValue;

  public String getMixDesignProportionHasZeroValue() {
    return mixDesignProportionHasZeroValue;
  }

  public void setMixDesignProportionHasZeroValue(String mixDesignProportionHasZeroValue) {
    this.mixDesignProportionHasZeroValue = mixDesignProportionHasZeroValue;
  }

  @Value("${validation.rawMaterial.plantOrSbuNull}")
  private String rawMaterialPlantOrSbuNull;

  public String getRawMaterialPlantOrSbuNull() {
    return rawMaterialPlantOrSbuNull;
  }

  public void setRawMaterialPlantOrSbuNull(String rawMaterialPlantOrSbuNull) {
    this.rawMaterialPlantOrSbuNull = rawMaterialPlantOrSbuNull;
  }

  @Value("${validation.rawMaterial.plantOrSbuAlreadyDepended}")
  private String rawMaterialPlantOrSbuAlreadyDepended;

  public String getRawMaterialPlantOrSbuAlreadyDepended() {
    return rawMaterialPlantOrSbuAlreadyDepended;
  }

  public void setRawMaterialPlantOrSbuAlreadyDepended(String rawMaterialPlantOrSbuAlreadyDepended) {
    this.rawMaterialPlantOrSbuAlreadyDepended = rawMaterialPlantOrSbuAlreadyDepended;
  }

  /*
   * Material Quality Parameter
   */
  @Value("${validation.materialQualityParameter.notExists}")
  private String materialQualityParameterNotExist;

  @Value("${validation.commonFields.notExists}")
  private String commonFieldsNotExist;

  @Value("${validation.materialQualityParameterTypes.notExists}")
  private String materialQualityParameterTypesNotExist;

  @Value("${validation.materialQualityConditionRanges.notExists}")
  private String materialQualityConditionRangesNotExist;

  @Value("${validation.materialQualityParameter.alreadyExists}")
  private String materialQualityParameterAlreadyExists;

  public String getCommonFieldsNotExist() {
    return commonFieldsNotExist;
  }

  public void setCommonFieldsNotExist(String commonFieldsNotExist) {
    this.commonFieldsNotExist = commonFieldsNotExist;
  }

  public String getMaterialQualityParameterTypesNotExist() {
    return materialQualityParameterTypesNotExist;
  }

  public void setMaterialQualityParameterTypesNotExist(
      String materialQualityParameterTypesNotExist) {
    this.materialQualityParameterTypesNotExist = materialQualityParameterTypesNotExist;
  }

  public String getMaterialQualityConditionRangesNotExist() {
    return materialQualityConditionRangesNotExist;
  }

  public void setMaterialQualityConditionRangesNotExist(
      String materialQualityConditionRangesNotExist) {
    this.materialQualityConditionRangesNotExist = materialQualityConditionRangesNotExist;
  }

  public String getMaterialQualityParameterNotExist() {
    return materialQualityParameterNotExist;
  }

  public void setMaterialQualityParameterNotExist(String materialQualityParameterNotExist) {
    this.materialQualityParameterNotExist = materialQualityParameterNotExist;
  }

  public String getMaterialQualityParameterAlreadyExists() {
    return materialQualityParameterAlreadyExists;
  }

  public void setMaterialQualityParameterAlreadyExists(
      String materialQualityParameterAlreadyExists) {
    this.materialQualityParameterAlreadyExists = materialQualityParameterAlreadyExists;
  }

  @Value("${validation.parameter.parameterAlreadyDepended}")
  private String parameterAlreadyDepended;

  public String getParameterAlreadyDepended() {
    return parameterAlreadyDepended;
  }

  public void setParameterAlreadyDepended(String parameterAlreadyDepended) {
    this.parameterAlreadyDepended = parameterAlreadyDepended;
  }

}
