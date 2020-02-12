package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	// check is designation already exists
	@Transactional(readOnly = true)
	public boolean isDesignationAlreadyExist(String designation) {
		return designationRepository.existsByName(designation);
	}

	// check is id already exists
	@Override
	public boolean isDesignationIdAlreadyExist(Long id) {
		return designationRepository.existsById(id);
	}

	//get designation by id
	@Override
	public Designation getDesignationById(Long id) {
		return designationRepository.findDesignationById(id);
	}

}
