package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.entities.EquationParameter;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.EquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class TestConfigureServiceImpl implements TestConfigureService {
	@Autowired
	private TestConfigureRepository testConfigureRepository;

	@Autowired
	private AcceptedValueRepository acceptedValueRepository;

	@Autowired
	private EquationRepository equationRepository;
	
	@Autowired
	private EquationParameterService equationParameterService;

	@Autowired
	private Mapper mapper;

	@Transactional
	public Long saveTestConfigure(TestConfigure testConfigure) {
		testConfigureRepository.save(testConfigure);
		return testConfigure.getId();
	}

	@Transactional(readOnly = true)
	public boolean isTestConfigureExist(Long id) {
		return testConfigureRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<TestConfigure> getAllTestConfigures() {
		return testConfigureRepository.findAll();
	}

	@Transactional(readOnly = true)
	public TestConfigure getTestConfigureById(Long id) {
		return testConfigureRepository.findById(id).get();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteTestConfigure(Long id) {
		testConfigureRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<TestConfigure> getTestConfigureByTestType(TestType testType) {
		return testConfigureRepository.findByTestType(testType);
	}

	@Transactional(readOnly = true)
	public List<TestConfigure> findByTestTypeId(Long testTypeId) {
		return testConfigureRepository.findByTestTypeId(testTypeId);
	}

	public boolean isDuplicateEntryExist(Long testId, Long testTypeId) {
		if ((!findByTestTypeId(testTypeId).equals(testId))
				&& (testConfigureRepository.existsByTestTypeIdAndTestId(testId, testTypeId))) {
			return true;
		}
		return false;
	}

	@Transactional
	public void updateCoreTestForTestConfigure(Long id, boolean coreTest) {
		TestConfigure testConfigure = testConfigureRepository.findById(id).get();
		testConfigure.setCoreTest(coreTest);
		testConfigureRepository.save(testConfigure);
	}

	@Transactional(readOnly = true)
	public List<TestConfigure> findByCoreTest(boolean coreTest) {
		return testConfigureRepository.findByCoreTest(coreTest);
	}

	@Transactional(readOnly = true)
	public Page<TestConfigure> searchTestConfigure(Predicate predicate, int page, int size) {
		return testConfigureRepository.findAll(predicate,
				PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
	}

	@Transactional(readOnly = true)
	public TestConfigureDto getTestDetailsByConfigureId(Long id) {
		TestConfigureDto testConfigureDto = new TestConfigureDto();
		TestConfigure testConfigure = testConfigureRepository.findById(id).get();
	AcceptedValue acceptedValue = acceptedValueRepository.findByTestConfigureId(id);
	Equation equation = equationRepository.findByTestConfigureId(id);
	
	List<String> parameter= new ArrayList<>();
	
		
		testConfigureDto.setId(testConfigure.getId());
		testConfigureDto.setPrefix(testConfigure.getPrefix());
		testConfigureDto.setTestName(testConfigure.getTest().getName());
		testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
		testConfigureDto.setTestType(testConfigure.getTestType().getType());
		if (!testConfigure.getTestType().getMaterialSubCategory().getMaterialCategory().getName()
				.equalsIgnoreCase("Admixture")) {
			testConfigureDto.setEquation(equation.getFormula());
			List<EquationParameter> parameters= equationParameterService.getEquationByEquationId(equation.getId());
			for(EquationParameter equationParameter: parameters) 
	     	{
	     		
	     		parameter.add(equationParameter.getTestParameter().getParameter().getName());
	     		}
			testConfigureDto.setParameters(parameter);
			
		}
	testConfigureDto.setAcceptedValue(mapper.map(acceptedValue, AcceptedValueDto.class));

		testConfigureDto.setCoreTest(testConfigure.isCoreTest());
		testConfigureDto.setDescription(testConfigure.getDescription());
		

		return testConfigureDto;
	}
}
