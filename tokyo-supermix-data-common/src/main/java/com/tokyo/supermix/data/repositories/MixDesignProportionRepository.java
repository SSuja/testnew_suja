package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignProportion;

public interface MixDesignProportionRepository extends JpaRepository<MixDesignProportion, Long>{
List<MixDesignProportion> findByMixDesign(MixDesign mixDesign);
}

