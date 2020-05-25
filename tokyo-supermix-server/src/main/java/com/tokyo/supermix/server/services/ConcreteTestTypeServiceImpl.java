package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.ConcreteTestType;
import com.tokyo.supermix.data.repositories.ConcreteTestTypeRespository;

@Service
public class ConcreteTestTypeServiceImpl implements ConcreteTestTypeService {
	@Autowired
	private ConcreteTestTypeRespository concreteTestTypeRepository;

	@Transactional
	public ConcreteTestType saveConcreteTestType(ConcreteTestType concreteTestType) {
		return concreteTestTypeRepository.save(concreteTestType);
	}

	@Transactional(readOnly = true)
	public List<ConcreteTestType> getAllConcreteTestTypes() {
		return concreteTestTypeRepository.findAll();
	}

	@Transactional(readOnly = true)
	public ConcreteTestType getConcreteTestTypeById(Long id) {
		return concreteTestTypeRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteConcreteTestType(Long id) {
		concreteTestTypeRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean isConcreteTestTypeExists(Long id) {
		return concreteTestTypeRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean isTypeExists(String type) {
		return concreteTestTypeRepository.existsByType(type);
	}

	@Transactional(readOnly = true)
	public Page<ConcreteTestType> searchConcreteTestType(Predicate predicate, int size, int page) {
		return concreteTestTypeRepository.findAll(predicate,
				PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}
}
