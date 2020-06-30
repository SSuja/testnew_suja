package com.tokyo.supermix.data.entities.auth;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.enums.RoleType;

@Entity
@Table(name = "user_role", schema = "tokyo-supermix")
public class UserRole implements Serializable {
  private static final long serialVersionUID = 1L;
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
  @Enumerated(EnumType.ORDINAL)
  private RoleType roleType;
  public UserRole() {}
  public UserRole(User user, Role role) {
    super();
    this.user = user;
    this.role = role;
    if(role.getName().equalsIgnoreCase("USER")) {
      this.roleType =RoleType.INDIVIDUAL;
    }else {
      this.roleType =RoleType.GROUP;
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
  public RoleType getRoleType() {
    return roleType;
  }
  public void setRoleType(RoleType roleType) {
    this.roleType = roleType;
  }
  
}
