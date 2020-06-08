package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.ConcreteTestStatus;
import com.tokyo.supermix.data.enums.ConcreteStatus;

public interface ConcreteTestStatusRepository extends JpaRepository<ConcreteTestStatus, Long> {
  ConcreteTestStatus findByFinishProductSampleId(Long finishProductSampleId);

  ConcreteTestStatus findByConcreteTestTypeId(Long concreteTestTypeId);

  ConcreteTestStatus findByConcreteTestTypeTypeAndFinishProductSampleId(String Type,
      Long finishProductSampleId);

  List<ConcreteTestStatus> findByConcreteStatus(ConcreteStatus concreteStatus);

  boolean existsByConcreteTestTypeType(String concreteTestType);

  List<ConcreteTestStatus> findByConcreteStatusAndConcreteTestTypeType(
      ConcreteStatus concreteStatus, String concreteTestType);

  boolean existsByConcreteTestTypeIdAndFinishProductSampleId(Long concreteTestTypeId,
      Long finishProductSampleId);

  List<ConcreteTestStatus> findByConcreteTestTypeType( String concreteTestType);

}
