package com.auth.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.auth.security.config.UserPrincipal;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.repositories.auth.UserRepository;

@Service("authUserDetailsService")
public class AuthUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() -> new UsernameNotFoundException(
            "User not found with username or email :" + usernameOrEmail));
    return UserPrincipal.create(user);
  }

  // This method is used by JWTAuthenticationFilter
  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id :" + id));
    return UserPrincipal.create(user);

  }
}
