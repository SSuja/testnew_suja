package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.TestParameter;

@Repository
public interface TestParameterRepository extends JpaRepository<TestParameter, Long> {
	List<TestParameter> findByTestId(Long testid);

	boolean existsByTestId(Long testid);

	@Query(value = "SELECT *  FROM test_parameter WHERE parameter_id= ?1 and test_id= ?2 and unit_id= ?3", nativeQuery = true)
	Long isDuplicateRow(Long paramId, Long testId, Long unitId);
}
