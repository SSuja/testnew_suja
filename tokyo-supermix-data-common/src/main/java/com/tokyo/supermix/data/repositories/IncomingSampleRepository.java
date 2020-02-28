package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.IncomingSample;

public interface IncomingSampleRepository extends JpaRepository<IncomingSample, String> {
  boolean existsByCode(String code);
  IncomingSample findIncomingSampleByCode(String code);
}
