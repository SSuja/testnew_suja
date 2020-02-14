package com.tokyo.supermix.server.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tokyo.supermix.data.entities.Designation;
import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	private DesignationRepository designationRepository;

	@Override
	public boolean isDesignationExist(Long id) {
		return designationRepository.existsById(id);
	}

	@Transactional()
	public Designation getDesignationById(Long id) {
		return designationRepository.findById(id).get();
	}

}
