package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService{

	@Autowired
	private DesignationRepository designationRepository;

	@Transactional
	public void createDesignation(Designation designation) {
		designationRepository.save(designation);
	}
 
	@Transactional(readOnly = true)
	public boolean isDesignationAlreadyExist(String designation) {
		return designationRepository.existsByName(designation);
	}

}