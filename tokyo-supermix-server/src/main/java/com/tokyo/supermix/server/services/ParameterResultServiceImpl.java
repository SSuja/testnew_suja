package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.dto.MaterialParameterResultDto;
import com.tokyo.supermix.data.dto.ParameterResultDto;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.enums.TestParameterType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestTrialRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.ParameterResultRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class ParameterResultServiceImpl implements ParameterResultService {
	@Autowired
	private ParameterResultRepository parameterResultRepository;
	@Autowired
	private TestParameterService testParameterService;
	@Autowired
	MaterialQualityParameterRepository materialQualityParameterRepository;
	@Autowired
	MaterialTestTrialRepository materialTestTrialRepository;
	@Autowired
	MaterialTestRepository materialTestRepository;
	@Autowired
	TestParameterRepository testParameterRepository;
	@Autowired
	ParameterEquationRepository parameterEquationRepository;
	@Autowired
	ParameterEquationElementRepository parameterEquationElementRepository;

	@Transactional
	public void saveParameterValue(ParameterResult parameterValue) {
		parameterResultRepository.save(parameterValue);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getAllParameterResults() {
		return parameterResultRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteParameterResult(Long id) {
		parameterResultRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public ParameterResult getParameterResultById(Long id) {
		return parameterResultRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public boolean isParameterResultExist(Long id) {
		return parameterResultRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> findByMaterialTestTrialCode(String materialTestTrialCode) {
		return parameterResultRepository.findByMaterialTestTrialCode(materialTestTrialCode);
	}

	// private Double roundDoubleValue(Double value) {
	// DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
	// return Double.valueOf(decimalFormat.format(value));
	// }

	public void isTestParameterValueInConfigLevel(ParameterResult parameterResult) {
		if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId()) != null) {
			if (testParameterService.getTestParameterById(parameterResult.getTestParameter().getId()).getEntryLevel()
					.equals(TestParameterType.CONFIGURE)) {
				parameterResult.setValue(testParameterService
						.getTestParameterById(parameterResult.getTestParameter().getId()).getValue());
			}
		}
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> findParameterResultByPlantCode(String plantCode) {
		return parameterResultRepository.findByMaterialTestTrialMaterialTestIncomingSamplePlantCode(plantCode);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getParameterResultWithConfigValue(String materialTestTrialCode,
			String materialTestCode) {
		List<ParameterResult> paramterResultList = parameterResultRepository
				.findByMaterialTestTrialCodeAndMaterialTestCode(materialTestTrialCode, materialTestCode);
		for (ParameterResult parameterResult : paramterResultList) {
			isTestParameterValueInConfigLevel(parameterResult);
		}
		return paramterResultList;
	}

	public List<ParameterResult> findByMaterialTestCode(String materialTestCode) {
		return parameterResultRepository.findByMaterialTestCode(materialTestCode);
	}

	@Transactional(readOnly = true)
	public List<ParameterResult> getTestParamWithEquationByTestTrial(String materialTestTrialCode) {
		return parameterResultRepository
				.findByMaterialTestTrialCodeAndTestParameterEquationExistsTrue(materialTestTrialCode);
	}

	// set values to equation less parameters
	public void setParameterResults(MaterialParameterResultDto materialParameterResultDto) {
		ArrayList<ParameterResult> parameterResultList = new ArrayList<ParameterResult>();
		// HashMap<Long, Double> map = new HashMap<>();
		for (ParameterResultDto parameterResultDto : materialParameterResultDto.parameterResults) {
			ParameterResult parameterResul = new ParameterResult();
			parameterResul.setMaterialTest(
					materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
			parameterResul.setMaterialTestTrial(
					materialTestTrialRepository.findByCode(materialParameterResultDto.getMaterialTestTrialCode()));
			parameterResul
					.setTestParameter(testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
			parameterResul.setValue(parameterResultDto.getValue());
			parameterResultList.add(parameterResul);
			parameterResultRepository.save(parameterResul);
		}
	}

	public void setParameterResultWhenEquationExist(MaterialParameterResultDto materialParameterResultDto,
			String materialTestTrialCode) {
		double result = 0;
		HashMap<Long, Double> map = new HashMap<>();
		List<ParameterResult> parameterResults = getTestParamWithEquationByTestTrial(
				materialParameterResultDto.getMaterialTestTrialCode());
		
			for (ParameterResult parameterResult : parameterResults) {
				ParameterEquation parameterEquation = parameterEquationRepository
						.findByTestParameterId(parameterResult.getTestParameter().getId());
				if (parameterEquation.getTestParameter().isEquationExists()) {
					List<ParameterEquationElement> parameterEquationElementList = parameterEquationElementRepository
							.findByParameterEquationId(parameterEquation.getId());
					for (ParameterEquationElement parameterEquationElement : parameterEquationElementList) {
						if (parameterEquationElement.getParameterEquation().getEquation().getName()
								.equalsIgnoreCase("Equation")) {
							if (!(parameterEquation.getTestParameter().isEquationExists())) {
								result = findResult(parameterEquationElement.getTestParameter().getAbbreviation(),
										parameterResult.getValue(),
										parameterEquationElement.getParameterEquation().getEquation().getFormula());
								System.out.println("valuryguygvsuuuuuuuuuuuuuuus" + parameterResult.getValue());
								// parameterResult.setValue(result);
							}
						}
					}
				
			}
		}
		List<ParameterResult> paramEquationResults = getTestParamWithEquationByTestTrial(materialTestTrialCode);
	}

	public double findResult(String abbreviation, double value, String equation) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		double result = 0;
		engine.put(abbreviation, value);
		try {
			result = (double) engine.eval(equation);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
	}
}
