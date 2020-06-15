package com.tokyo.supermix.data.entities.auth;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.entities.privilege.RolePermission;

@Entity
@Table(name = "role", schema = "tokyo-supermix")
public class Role implements Serializable {
  private static final long serialVersionUID = 1L;
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;
  private String name;
  @OneToMany(mappedBy = "role")
  Set<PlantRole> plantRole;
  @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
  private Set<RolePermission> rolePermission;
  @OneToMany(mappedBy = "role")
  private Set<UserRole> userRoles;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<RolePermission> getRolePermission() {
    return rolePermission;
  }

  public void setRolePermission(Set<RolePermission> rolePermission) {
    this.rolePermission = rolePermission;
  }

}
