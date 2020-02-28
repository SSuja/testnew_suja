package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Unit;

public interface UnitService {

	public void saveUnit(Unit unit);

	public boolean isUnitExist(Long id);

	public boolean isUnitExist(String unit);

	public List<Unit> getAllUnits();

	public void deleteUnit(Long id);

	public Unit getUnitById(Long id);

	public boolean isUpdatedUnitExist(Long id, String unit);
}
