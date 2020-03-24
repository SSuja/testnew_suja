package com.tokyo.supermix.data.repositories;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.enums.Status;

public interface IncomingSampleRepository extends JpaRepository<IncomingSample, String> {
  boolean existsByCode(String code);
  IncomingSample findIncomingSampleByCode(String code);
  IncomingSample findIncomingSampleByStatus(Status status);
  boolean existsByStatus(Status status);
  List<IncomingSample> findByStatusAndRawMaterialIdAndDate(Status status,Long RawMaterialId,Date date );
  List<IncomingSample> findByRawMaterialIdAndDate(Long RawMaterialId,Date date);
  List<IncomingSample> findByStatus(Status status);
}
