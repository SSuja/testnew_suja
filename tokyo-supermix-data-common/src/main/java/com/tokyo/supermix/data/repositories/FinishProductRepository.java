package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.FinishProduct;

public interface FinishProductRepository extends JpaRepository<FinishProduct, Long> {

	boolean existsByProjectCode(String projectCode);

	boolean existsByMixDesignCode(String mixDesignCode);

	boolean existsByPourId(Long pourId);

}
