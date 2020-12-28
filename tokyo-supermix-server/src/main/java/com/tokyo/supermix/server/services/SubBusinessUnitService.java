package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.SubBusinessUnit;

public interface SubBusinessUnitService {

  public List<SubBusinessUnit> getAllSubBusinessUnits();

  public boolean isSubBusinessUnitExist(Long id);

  public SubBusinessUnit getSubBusinessUnitById(Long id);

  public void deleteSubBusinessUnit(Long id);

  public void saveSubBusinessUnit(SubBusinessUnit subBusinessUnit);

  public boolean isSubBusinessUnitExist(String subBusinessUnit);

  public boolean isUpdatedSubBusinessUnitNameExist(Long id, String subBusinessUnit);
}
