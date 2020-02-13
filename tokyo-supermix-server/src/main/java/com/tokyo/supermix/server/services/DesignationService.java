package com.tokyo.supermix.server.services;

public interface DesignationService {

	public boolean isDesignationIdAlreadyExist(Long id);

	public void deleteDesignation(Long id);

}
