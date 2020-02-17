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

	@Value("${validation.supplierCategory.alreadyExist}")
	private String supplierCategoryAlreadyExist;

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

	public String getSupplierCategoryAlreadyExist() {
		return supplierCategoryAlreadyExist;
	}

	public void setSupplierCategoryAlreadyExist(String supplierCategoryAlreadyExist) {
		this.supplierCategoryAlreadyExist = supplierCategoryAlreadyExist;
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

}
