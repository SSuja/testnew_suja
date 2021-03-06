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

	@Value("${validation.plant.notExists}")
	private String plantNotExist;

	@Value("${validation.plant.alreadyExist}")
	private String plantAlreadyExist;

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

}
