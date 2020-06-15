package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private Long id;
  private String name;
  @OneToMany(mappedBy = "permission")
  private Set<RolePermission> rolePermission;
  @ManyToOne
  @JoinColumn(name = "sub_route_id", nullable = false)
  private SubRoute subRoute;

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

  public SubRoute getSubRoute() {
    return subRoute;
  }

  public void setSubRoute(SubRoute subRoute) {
    this.subRoute = subRoute;
  }

}
