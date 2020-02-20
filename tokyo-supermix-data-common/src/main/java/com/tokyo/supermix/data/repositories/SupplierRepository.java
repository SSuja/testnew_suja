package com.tokyo.supermix.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tokyo.supermix.data.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	Supplier findSupplierById(Long id);

	boolean existsByName(String name);

	boolean existsByEmail(String email);

	boolean existsByPhoneNumber(String phoneNumber);

	@Query(value = " from Supplier where suppiler_category_id =:id")
	List<Supplier> getBySuppilerCategory(@Param("id") Long id);

	@Query(value = "SELECT EXISTS (select * from suppiler where suppiler_category_id =:id)", nativeQuery = true)
	Integer existBySuppilerCategoryId(@Param("id") Long id);
	@Query("SELECT count(name) FROM Supplier s WHERE s.id <> ?1 AND s.name= ?2")
	Integer checkSupplierNameWithLockId(Long id,String name);
	@Query("SELECT count(email) FROM Supplier s WHERE s.id <> ?1 AND s.email= ?2")
	Integer checkPhoneNumberWithLockId(Long id,String phoneNumber);
	@Query("SELECT count(email) FROM Supplier s WHERE s.id <> ?1 AND s.phoneNumber= ?2")
	Integer checkEmailWithLockId(Long id,String phoneNumber);
}
