package com.tokyo.supermix.server.services;

import java.text.DecimalFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ConcreteTest;
import com.tokyo.supermix.data.entities.MixDesignProportion;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.ConcreteTestRepository;
import com.tokyo.supermix.data.repositories.MixDesignProportionRepository;
import com.tokyo.supermix.util.Constants;

@Service
public class ConcreteTestServiceImpl implements ConcreteTestService {
	@Autowired
	private ConcreteTestRepository concreteTestRepository;
	@Autowired
	private MixDesignService mixDesignService;
	@Autowired
	private MixDesignProportionRepository mixDesignProportionRepository;

	@Transactional
	public ConcreteTest saveConcreteTest(ConcreteTest concreteTest) {
		return concreteTestRepository.save(calculateRatio(concreteTest));
	}

	public ConcreteTest calculateRatio(ConcreteTest concreteTest) {
		List<MixDesignProportion> mixDesignProportionList = mixDesignProportionRepository
				.findByMixDesignCode(concreteTest.getFinishProductSample().getMixDesign().getCode());
		Long quantity = null;
		Long binderquantity = 0L;
		for (MixDesignProportion mixDesignProportion : mixDesignProportionList) {
			if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase(Constants.RAW_MATERIAL_CEMENT)) {
				quantity = mixDesignProportion.getQuantity();
				concreteTest.setWaterCementRatio(
						calculateWaterCementRatio(concreteTest.getWaterContent(), quantity.doubleValue()));
			}
			if (mixDesignProportion.getRawMaterial().getName().equalsIgnoreCase(Constants.RAW_MATERIAL_FLYASH)) {
				binderquantity = mixDesignProportion.getQuantity();
			}
		}
		concreteTest.setWaterBinderRatio(calculateWaterBinderRatio(concreteTest.getWaterContent(),
				binderquantity.doubleValue(), quantity.doubleValue()));
		concreteTest.setSlumpGradeRatio(calculateSlumpGradeRatio(
				concreteTest.getFinishProductSample().getMixDesign().getCode(), concreteTest.getSlump()));
		concreteTest.setStatus(calculateConcreteStatus(concreteTest.getFinishProductSample().getMixDesign().getCode(),
				concreteTest.getSlump()));
		return concreteTest;
	}

	// To find calculateConcreteStatus
	private Status calculateConcreteStatus(String mixDesignCode, Double slump) {
		Double targetSlump = mixDesignService.getMixDesignByCode(mixDesignCode).getTargetSlump();
		if (targetSlump - 25 <= slump && slump <= targetSlump + 25) {
			return Status.PASS;
		} else {
			return Status.FAIL;
		}
	}

	// To find WaterCementRatio
	private Double calculateWaterCementRatio(Double waterContent, Double cementQuantity) {
		return roundDoubleValue(100 * waterContent / cementQuantity);
	}

	// To find WaterBinderRatio
	private Double calculateWaterBinderRatio(Double waterContent, Double binderquantity, Double quantity) {
		return roundDoubleValue(100 * waterContent / (quantity + binderquantity));
	}

	// To find SlumpGradeRatio
	private Double calculateSlumpGradeRatio(String mixDesignCode, Double slump) {
		Double targetSlump = mixDesignService.getMixDesignByCode(mixDesignCode).getTargetSlump();
		return roundDoubleValue(slump / targetSlump);
	}

	// To Change Four Digit Value
	private Double roundDoubleValue(Double value) {
		DecimalFormat decimalFormat = new DecimalFormat(Constants.DECIMAL_FORMAT);
		return Double.valueOf(decimalFormat.format(value));
	}

	@Transactional(readOnly = true)
	public List<ConcreteTest> getAllConcreteTest() {
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
	public boolean isConcreteTestExit(Long id) {
		return concreteTestRepository.existsById(id);
	}
}
