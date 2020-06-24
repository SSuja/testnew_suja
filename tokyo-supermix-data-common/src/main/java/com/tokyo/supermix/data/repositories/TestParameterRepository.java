package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.TestParameter;
<<<<<<< HEAD
=======
import com.tokyo.supermix.data.enums.TestParameterType;
>>>>>>> a7160384f70ad396b4907c9001a3a2633500b4fc

@Repository
public interface TestParameterRepository
		extends JpaRepository<TestParameter, Long>, QuerydslPredicateExecutor<TestParameter> {
	List<TestParameter> findByTestConfigureId(Long testConfigureId);

	public boolean existsByTestConfigureId(Long testConfigureId);

	public boolean existsByParameterId(Long parameterId);

<<<<<<< HEAD
  public boolean existsByTestConfigureIdAndAbbreviation(Long testConfigureId,String abbreviation);
=======
	public boolean existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviationAndEntryLevel(Long testConfigureId,
			Long parameterId, Long unitId, String abbreviation, TestParameterType entryLevel);
>>>>>>> a7160384f70ad396b4907c9001a3a2633500b4fc

	public boolean existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviation(Long testConfigureId,
			Long qualityParameterId, Long unitId, String abbreviation);

	public List<TestParameter> findAllByOrderByIdDesc();

	public boolean existsByAbbreviation(String abbreviation);

	List<TestParameter> findByTestConfigureIdAndEquationExistsTrue(Long testConfigureId);
}
