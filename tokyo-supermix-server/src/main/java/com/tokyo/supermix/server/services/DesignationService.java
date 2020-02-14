package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.Designation;

public interface DesignationService {

	public boolean isDesignationExist(Long id);

	public Designation getDesignationById(Long id);

}
