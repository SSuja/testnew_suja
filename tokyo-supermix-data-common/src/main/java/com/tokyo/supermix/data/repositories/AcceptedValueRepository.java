package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.AcceptedValue;

public interface AcceptedValueRepository extends JpaRepository<AcceptedValue, Long> {
}
