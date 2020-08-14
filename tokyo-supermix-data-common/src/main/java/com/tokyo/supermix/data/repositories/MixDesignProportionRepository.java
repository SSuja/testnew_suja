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
      "SELECT p.id,p.raw_material_id,p.quantity,p.unit_id,p.mix_design_code, m.date, m.target_grade, m.target_slump,m.water_binder_ratio,m.water_cement_ratio,m.plant_code,m.material_category_id,m.created_at,m.updated_at,m.status FROM mix_design_proportion p INNER JOIN mix_design m ON  m.code=p.mix_design_code";
  @Query(value = query, nativeQuery = true)
  List<MixDesignProportion> getMixDesign();
}

