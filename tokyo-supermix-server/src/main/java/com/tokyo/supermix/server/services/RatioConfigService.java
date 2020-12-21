package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RatioConfig;

public interface RatioConfigService {

	public RatioConfig saveRatioConfig(RatioConfig ratioConfig);

	public List<RatioConfig> getAllRatioConfigs();

	public boolean isRatioConfigExist(Long id);

	public RatioConfig getRatioConfigById(Long id);

	public void deleteRatioConfig(Long id);

	public boolean isRatioConfigExist(String name);

	public boolean isUpdatedRatioConfigNameExist(Long id, String name);
}
