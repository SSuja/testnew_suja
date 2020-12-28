package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tokyo.supermix.data.entities.MixDesignConfirmationToken;

@Repository
public interface MixDesignConfirmationTokenRepository extends JpaRepository<MixDesignConfirmationToken, Long> {
	MixDesignConfirmationToken findByConfirmationToken(String confirmationToken);

	MixDesignConfirmationToken findBymixDesignTokenId(Long id);
}
