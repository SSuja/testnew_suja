package com.tokyo.supermix.data.repositories;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.User;
import com.tokyo.supermix.data.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
  VerificationToken findByToken(String token);
  VerificationToken findByUser(User user);
  void deleteByExpiryDateLessThan(Date now);
}
