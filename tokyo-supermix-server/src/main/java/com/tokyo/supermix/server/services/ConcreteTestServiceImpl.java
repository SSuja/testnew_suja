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
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;

@Service
public class ConcreteTestServiceImpl implements ConcreteTestService {

	@Autowired
	private ConcreteTestRepository concreteTestRepository;

	@Transactional
	public ConcreteTest saveConcreteTest(ConcreteTest concreteTest) {
		return concreteTestRepository.save(concreteTest);
	}

	@Transactional(readOnly = true)
	public List<ConcreteTest> getAllConcreteTests() {
		return concreteTestRepository.findAll();
	}

	@Transactional(readOnly = true)
	public ConcreteTest getConcreteTestById(Long id) {
		return concreteTestRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteConcreteTest(Long id) {
		concreteTestRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public boolean isConcreteTestExists(Long id) {
		return concreteTestRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean isNameExists(String name) {
		return concreteTestRepository.existsByName(name);
	}

	@Transactional(readOnly = true)
	public Page<ConcreteTest> searchConcreteTest(Predicate predicate, int size, int page) {
		return concreteTestRepository.findAll(predicate, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}
}
