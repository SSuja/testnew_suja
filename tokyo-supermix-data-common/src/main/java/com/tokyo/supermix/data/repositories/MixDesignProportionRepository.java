package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.MixDesignProportion;

public interface MixDesignProportionRepository extends JpaRepository<MixDesignProportion, Long>,
    QuerydslPredicateExecutor<MixDesignProportion> {
  List<MixDesignProportion> findByMixDesignCode(String mixDesignCode);

  boolean existsByMixDesignCode(String mixDesignCode);

  String query =
      "SELECT p.id,p.raw_material_id,p.quantity,p.unit_id,p.mix_design_code, m.date,m.plant_code,m.raw_material_id,m.created_at,m.updated_at,m.status FROM mix_design_proportion p INNER JOIN mix_design m ON  m.code=p.mix_design_code";
  @Query(value = query, nativeQuery = true)
  List<MixDesignProportion> getMixDesign();
}

