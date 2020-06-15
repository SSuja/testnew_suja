package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.auth.Role;

@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {
  private static final long serialVersionUID = 1L;
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;
  @ManyToOne
  @JoinColumn(name = "permission_id")
  private Permission permission;
  private boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
}
