package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.MixDesignParameterResult;
import com.tokyo.supermix.data.repositories.MixDesignParameterResultRepository;

@Service
public class MixDesignParameterResultServiceImpl implements MixDesignParameterResultService {

	@Autowired
	private MixDesignParameterResultRepository mixDesignParameterResultRepository;

	@Transactional
	public void saveMixDesign(MixDesignParameterResult mixDesignParameterResult) {
		mixDesignParameterResultRepository.save(mixDesignParameterResult);
	}

	@Transactional(readOnly = true)
	public MixDesignParameterResult getMixDesignParamResultById(Long id) {
		return mixDesignParameterResultRepository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public List<MixDesignParameterResult> getAllMixDesignParamResults() {
		return mixDesignParameterResultRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMixDesignParameterResult(Long id) {
		mixDesignParameterResultRepository.deleteById(id);
	}

}
