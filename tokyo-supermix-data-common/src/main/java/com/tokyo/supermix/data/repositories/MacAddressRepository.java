package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tokyo.supermix.data.entities.MacAddress;

@Repository
public interface MacAddressRepository extends JpaRepository<MacAddress, Long> {
	boolean existsByMacAddress(String macAddress);
}
