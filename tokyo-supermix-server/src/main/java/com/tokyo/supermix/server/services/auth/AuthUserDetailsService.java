package com.tokyo.supermix.server.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.repositories.auth.UserRepository;
import com.tokyo.supermix.security.UserPrincipal;

@Service
public class AuthUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() -> new UsernameNotFoundException(
            "User not found with username or email :" + usernameOrEmail));
    return user.getIsActive()  ? UserPrincipal.create(user) : null;
  }


  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id :" + id));
    return user.getIsActive() ? UserPrincipal.create(user) : null;
  }
}
