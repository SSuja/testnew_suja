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
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestTrial;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.ParameterResult;
import com.tokyo.supermix.data.entities.TestParameter;
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
		HashMap<String, Double> map = new HashMap<>();
		for (ParameterResultDto parameterResultDto : materialParameterResultDto.parameterResults) {
			ParameterResult parameterResult = new ParameterResult();
			parameterResult.setMaterialTest(
					materialTestRepository.findByCode(materialParameterResultDto.getMaterialTestCode()));
			parameterResult.setMaterialTestTrial(
					materialTestTrialRepository.findByCode(materialParameterResultDto.getMaterialTestTrialCode()));

			parameterResult
					.setTestParameter(testParameterRepository.findById(parameterResultDto.getTestParameterId()).get());
			parameterResult.setValue(parameterResultDto.getValue());
			parameterResultList.add(parameterResult);
			parameterResultRepository.save(parameterResult);
		}
		getvalueNew(materialParameterResultDto.getMaterialTestTrialCode());

	}

	public String getEquation(Long paramterEquationId) {
		return parameterEquationRepository.findById(paramterEquationId).get().getEquation().getFormula();
	}

//	public String setParameterResultWhenEquationExist(List<ParameterResult> parameterResultList,
//			String materialTestTrialCode) {
//		String equation = "";
//		return equation;
//	}

//	public void getvalue(String materialTestTrialCode) {
//	    MaterialTestTrial materialTestTrial=materialTestTrialRepository.getOne(materialTestTrialCode);
//	    List<TestParameter> testparameters=testParameterRepository.findByTestConfigureId(materialTestTrial
//	            .getMaterialTest().getTestConfigure().getId());
//	    List<TestParameter> testParametershasEqu=testParameterRepository.findByTestConfigureIdAndEquationExistsTrue
//	            (materialTestTrial
//	            .getMaterialTest().getTestConfigure().getId());
//	    String mainEquation="";
//	    mainEquation=materialTestTrial
//	            .getMaterialTest().getTestConfigure().getEquation().getFormula();
//	    List<ParameterEquation> parameterEquations=new ArrayList<>();
//	    testParametershasEqu.forEach(testparameter ->{
//	        ParameterEquation parameterEquation=parameterEquationRepository
//	                .findByTestParameterId(testparameter.getId());
//	        parameterEquations.add(parameterEquation);
//	        
//	    
//	    List<ParameterEquationElement> parameterEquationElementlist=new ArrayList<>();
//	    String paraEq="";
//	    for(ParameterEquation parEqu:parameterEquations) {
////	    parEquations.forEach(parEqu -> {
//	        List<ParameterEquationElement> parameterEquationElementlist1=parameterEquationElementRepository.findByParameterEquationId(parEqu.getId());
//	        parameterEquationElementlist.addAll(parameterEquationElementlist1);
//	        paraEq=parEqu.getEquation().getFormula();
//	    }
//	    HashMap<String, Double> sum = new HashMap<String, Double>();
//	    for(ParameterEquationElement paramEquationEle:parameterEquationElementlist) {
////	        parameterEquationElementlist.forEach(paramEquationEle -> {
//	        Long testParameterId=paramEquationEle.getTestParameter().getId();
//	        paramEquationEle.getTestParameter().getValue();
//	        paramEquationEle.getTestParameter().getAbbreviation();
//	        ParameterResult parameterResult=parameterResultRepository.findByTestParameterId(testParameterId);
//	        sum.put(paramEquationEle.getTestParameter().getAbbreviation(), 
//	                parameterResult.getValue());
//	            
//	        }
//	    ParameterResult parameterResultsum=parameterResultRepository.findByTestParameterId(testparameter.getId());
//	    parameterResultsum.setValue(findResult(sum, paraEq));
//	    parameterResultRepository.save(parameterResultsum);
//	    System.out.println("PPPPP"+sum);
//	    System.out.println("****newpakee*"+findResult(sum, paraEq));
//	    });
//	    
//
//	 
//
//	    HashMap<String, Double> main = new HashMap<String, Double>();
//	    for(TestParameter tepa :testparameters) {
//	        tepa.getAbbreviation();
//	        ParameterResult parameterResultmain=parameterResultRepository.findByTestParameterId(tepa.getId());
//	        //parameterResultmain.getValue();
//	        main.put(tepa.getAbbreviation(),parameterResultmain.getValue());
//	}
//	    System.out.println("****AM***"+findResult(main, mainEquation));
//	    materialTestTrial.setResult(findResult(main, mainEquation));
//	    materialTestTrialRepository.save(materialTestTrial);
//	    }

	
	public double findResult(HashMap<String,Double> abb, String equation){
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		double result = 0;
		for (String i : abb.keySet()) {
		      engine.put(i,abb.get(i));
	}
	    try {
			result = (double) engine.eval(equation);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		return result;
		}
	
	public void getvalueNew(String materialTestTrialCode) {
      MaterialTestTrial materialTestTrial=materialTestTrialRepository.getOne(materialTestTrialCode);
      List<TestParameter> testparameters=testParameterRepository.findByTestConfigureId(materialTestTrial
              .getMaterialTest().getTestConfigure().getId());
      List<TestParameter> testParametershasEqu=testParameterRepository.findByTestConfigureIdAndEquationExistsTrue
              (materialTestTrial
              .getMaterialTest().getTestConfigure().getId());
      String mainEquation="";
      mainEquation=materialTestTrial
              .getMaterialTest().getTestConfigure().getEquation().getFormula();
      List<ParameterEquation> parameterEquations=new ArrayList<>();
      testParametershasEqu.forEach(testparameter ->{
          ParameterEquation parameterEquation=parameterEquationRepository
                  .findByTestParameterId(testparameter.getId());
          parameterEquations.add(parameterEquation);
          String paraEq="";
          List<ParameterEquationElement> parameterEquationElementlist=parameterEquationElementRepository.findByParameterEquationId(parameterEquation.getId());
          paraEq=parameterEquation.getEquation().getFormula();

      HashMap<String, Double> sum = new HashMap<String, Double>();
      for(ParameterEquationElement paramEquationEle:parameterEquationElementlist) {
//        parameterEquationElementlist.forEach(paramEquationEle -> {
          Long testParameterId=paramEquationEle.getTestParameter().getId();
          paramEquationEle.getTestParameter().getValue();
          paramEquationEle.getTestParameter().getAbbreviation();
          ParameterResult parameterResult=parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(testParameterId,materialTestTrialCode);
          sum.put(paramEquationEle.getTestParameter().getAbbreviation(), 
                  parameterResult.getValue());
              
          }
      ParameterResult parameterResultsum=parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(testparameter.getId(),materialTestTrialCode);
      parameterResultsum.setValue(findResult(sum, paraEq));
      parameterResultRepository.save(parameterResultsum);
      System.out.println("PPPPP"+sum);
      System.out.println("****newpakee*"+findResult(sum, paraEq));
      });
      

   

      HashMap<String, Double> main = new HashMap<String, Double>();
      for(TestParameter tepa :testparameters) {
          tepa.getAbbreviation();
          ParameterResult parameterResultmain=parameterResultRepository.findByTestParameterIdAndMaterialTestTrialCode(tepa.getId(),materialTestTrialCode);
          //parameterResultmain.getValue();
          main.put(tepa.getAbbreviation(),parameterResultmain.getValue());
  }
      System.out.println("****AM***"+findResult(main, mainEquation));
      materialTestTrial.setResult(findResult(main, mainEquation));
      materialTestTrialRepository.save(materialTestTrial);
      }

}
