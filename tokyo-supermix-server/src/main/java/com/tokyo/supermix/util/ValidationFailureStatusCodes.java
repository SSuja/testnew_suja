
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

	public void setSupplierAlreadyExist(String supplierAlreadyExist) {
		this.supplierAlreadyExist = supplierAlreadyExist;
	}

	public String getSupplierAlreadyExist() {
		return supplierAlreadyExist;
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

	// For Material Category
	@Value("${validation.materialCategory.notExists}")
	private String MaterialCategoryNotExist;
	@Value("${validation.materialCategory.alreadyExist}")
	private String MaterialCategoryAlreadyExist;

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

}
