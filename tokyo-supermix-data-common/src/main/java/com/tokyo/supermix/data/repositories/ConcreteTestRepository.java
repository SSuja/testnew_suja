package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.ConcreteTest;

public interface ConcreteTestRepository extends JpaRepository<ConcreteTest, Long> {

}
