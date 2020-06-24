package com.tokyo.supermix.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.RoleType;
import com.tokyo.supermix.data.enums.UserType;

public class UserPrincipal implements UserDetails {
  private static final long serialVersionUID = -394792056682796726L;  
  private Long id;
  private String username;
  @JsonIgnore
  private String email;
  @JsonIgnore
  private String password;
  private boolean isActive;
  private Set<Long> roles;
  private Set<Long> plantRoles;
  private Set<String> plants;
  private UserType userType;
  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String username, String email, String password, boolean isActive,
      Collection<? extends GrantedAuthority> authorities,Set<Long> roles,Set<Long> plantRoles,Set<String> plants,UserType userType) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.isActive = isActive;
    this.authorities = authorities;
    this.roles = roles;
    this.plantRoles = plantRoles;
    this.plants = plants;
    this.userType = userType;
  }

  public static UserPrincipal create(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
    if (user.getUserType().name().equalsIgnoreCase(UserType.NON_PLANT_USER.name())) {
      user.getUserRoles().forEach(userrole -> {
        if (userrole.getRoleType().name().equalsIgnoreCase(RoleType.INDIVIDUAL.name())) {
          user.getUserPlantPermissions().forEach(userPlantPermission -> {
            if (userPlantPermission.getStatus()) {
              authorities.add(new SimpleGrantedAuthority(
                  userPlantPermission.getPlantPermission().getPermission().getName()));
            }
          });
        }
        authorities.add(new SimpleGrantedAuthority(userrole.getRole().getName()));
        userrole.getRole().getRolePermissions().forEach(rolePermission -> {
          if (rolePermission.isStatus()) {
            authorities.add(new SimpleGrantedAuthority(rolePermission.getPermission().getName()));
          }
        });
      });
    } else {
      user.getUserPlantRoles().forEach(userPlantrole -> {
        if (userPlantrole.getRoleType().name().equalsIgnoreCase(RoleType.INDIVIDUAL.name())) {
          user.getUserPlantPermissions().forEach(userPlantPermission -> {
            if (userPlantPermission.getStatus()) {
              authorities.add(new SimpleGrantedAuthority(
                  userPlantPermission.getPlantPermission().getPermission().getName()));
            }
          });
        }
        authorities.add(new SimpleGrantedAuthority(userPlantrole.getPlantRole().getName()));
        userPlantrole.getPlantRole().getPlantRolePlantPermissions()
            .forEach(plantRolePlantPermission -> {
              if (plantRolePlantPermission.isStatus()) {
                authorities.add(new SimpleGrantedAuthority(
                    plantRolePlantPermission.getPlantPermission().getPermission().getName()));
              }
            });
      });
    }
    System.out.println("permissions " + authorities.toString());
    Set<Long> roles = new HashSet<Long>();
    user.getUserRoles().forEach(userRole->roles.add(userRole.getRole().getId()));
    Set<Long> plantRoles = new HashSet<Long>();
    Set<String> plants = new HashSet<String>();
    user.getUserPlantRoles().forEach(userPlantRole->{
      plantRoles.add(userPlantRole.getPlantRole().getId());
      plants.add(userPlantRole.getPlantRole().getPlant().getCode());
      });
     return new UserPrincipal(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(),
        user.getIsActive(), authorities,roles,plantRoles,plants,user.getUserType());
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public Set<Long> getRoles() {
    return roles;
  }

  public void setRoles(Set<Long> roles) {
    this.roles = roles;
  }

  public Set<Long> getPlantRoles() {
    return plantRoles;
  }

  public void setPlantRoles(Set<Long> plantRoles) {
    this.plantRoles = plantRoles;
  }

  public void setPlants(Set<String> plants) {
    this.plants = plants;
  }

  public Set<String> getPlants() {
    return plants;
  }
 public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
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
    return isActive;
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
