package com.tokyo.supermix.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.UserType;

public class UserPrincipal implements UserDetails {
  private static final long serialVersionUID = -394792056682796726L;
  private Long id;
  private String username;
  @JsonIgnore
  private String email;
  @JsonIgnore
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String username, String email, String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    if(user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      user.getRoles().forEach(role->{
        authorities.add(new SimpleGrantedAuthority(role.getName()));     
      role.getRolePermissions().forEach(rolePermission -> {
        if(rolePermission.isStatus()) {
          System.out.println("permissions "+rolePermission.getPermission().getName());
          authorities.add(new SimpleGrantedAuthority(rolePermission.getPermission().getName()));
        }
      });
    });
//      user.getUserRoles().forEach(userRole->{
//      authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));     
//      userRole.getRole().getRolePermissions().forEach(rolePermission -> {
//      if(rolePermission.isStatus()) {
//        System.out.println("permissions "+rolePermission.getPermission().getName());
//        authorities.add(new SimpleGrantedAuthority(rolePermission.getPermission().getName()));
//      }
//    });
//  });
    }
    else {
      
    }
    return new UserPrincipal(user.getId(), user.getUserName(), user.getEmail(),
        user.getPassword(), authorities);
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }
  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserPrincipal that = (UserPrincipal) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
