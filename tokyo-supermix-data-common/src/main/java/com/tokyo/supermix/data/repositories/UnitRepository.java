package com.tokyo.supermix.data.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
	boolean existsByUnit(String unit);
	List<Unit> findAllByOrderByIdDesc();

}
