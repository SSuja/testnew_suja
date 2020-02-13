package com.tokyo.supermix.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.repositories.DesignationRepository;

@Service
public class DesignationServiceImpl implements DesignationService {
	@Autowired
	private DesignationRepository designationRepository;

	@Transactional(readOnly = true)
	public boolean isDesignationIdAlreadyExist(Long id) {
		return designationRepository.existsById(id);
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteDesignation(Long id) {
		designationRepository.deleteById(id);
	}

}
