package com.tokyo.supermix.data.repositories.auth;

import java.util.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.entities.auth.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
  VerificationToken findByToken(String token);
  VerificationToken findByUser(User user);
  void deleteByExpiryDateLessThan(Date now);
}
