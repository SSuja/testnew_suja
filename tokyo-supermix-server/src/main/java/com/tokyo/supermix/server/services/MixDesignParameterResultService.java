package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MixDesignParameterResult;

public interface MixDesignParameterResultService {

	public void saveMixDesign(MixDesignParameterResult mixDesignParameterResult);

	public MixDesignParameterResult getMixDesignParamResultById(Long id);

	public List<MixDesignParameterResult> getAllMixDesignParamResults();

	public void deleteMixDesignParameterResult(Long id);

}
