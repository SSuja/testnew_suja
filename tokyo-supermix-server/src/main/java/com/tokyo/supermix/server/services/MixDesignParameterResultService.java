package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.MixDesignParameterResult;

public interface MixDesignParameterResultService {

	public void saveMixDesignParameterResult(MixDesignParameterResult mixDesignParameterResult);

	public MixDesignParameterResult getMixDesignParamResultById(Long id);

	public List<MixDesignParameterResult> getAllMixDesignParamResults();

	public void deleteMixDesignParameterResult(Long id);

	public boolean isMixDesignParameterResultExists(Long id);

}
