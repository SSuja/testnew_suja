package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.enums.ConcreteStatus;

public interface ConcreteTestStatusRepository extends JpaRepository<ConcreteTestStatus, Long> {
  ConcreteTestStatus findByFinishProductSampleId(Long finishProductSampleId);

  ConcreteTestStatus findByConcreteTestTypeId(Long concreteTestTypeId);

  ConcreteTestStatus findByConcreteTestTypeTypeAndFinishProductSampleId(String Type,
      Long finishProductSampleId);

  ConcreteTestStatus findByConcreteStatus(ConcreteStatus concreteStatus);

  boolean existsByConcreteTestTypeIdAndFinishProductSampleId(Long concreteTestTypeId,
      Long finishProductSampleId);
}
