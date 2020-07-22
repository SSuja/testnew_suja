package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.TestEquation;

public interface MaterialTestResultRepository extends JpaRepository<MaterialTestResult,Long>{
	MaterialTestResult findByTestEquationAndMaterialTestCode(TestEquation testEquation,String materialTestCode);
	MaterialTestResult findByMaterialTestCode(String materialTestCode);

}

