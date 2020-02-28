package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;

@Service
public class IncomingSampleServiceImpl implements IncomingSampleService {
	@Autowired
	private IncomingSampleRepository incomingSampleRepository;

	@Transactional
	public void saveIncomingSample(IncomingSample incomingSample) {
		incomingSampleRepository.save(incomingSample);
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteIncomingSample(String code) {
		incomingSampleRepository.deleteById(code);
	}

	@Transactional(readOnly = true)
	public IncomingSample getIncomingSampleById(String code) {
		return incomingSampleRepository.findById(code).get();
	}

	@Transactional(readOnly = true)
	public List<IncomingSample> getAllIncomingSamples() {
		return incomingSampleRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean isIncomingSampleExist(String code) {
		return incomingSampleRepository.existsByCode(code);
	}
}
