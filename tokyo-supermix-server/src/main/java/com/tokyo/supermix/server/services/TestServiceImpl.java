package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Test;
import com.tokyo.supermix.data.repositories.TestRepository;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	@Transactional
	public void saveTest(Test test) {
		testRepository.save(test);
	}

	@Transactional(readOnly = true)
	public boolean isTestExist(Long id) {
		return testRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public boolean isTestExist(String name) {
		return testRepository.existsByName(name);
	}

	@Transactional(readOnly = true)
	public List<Test> getAllTests() {
		return testRepository.findAllByOrderByIdDesc();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteTest(Long id) {
		testRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Test getTestById(Long id) {
		return testRepository.findById(id).get();
	}

	public boolean isUpdatedTestExist(Long id, String name) {
		if ((!getTestById(id).getName().equalsIgnoreCase(name)) && (isTestExist(name))) {
			return true;
		}
		return false;
	}
}
