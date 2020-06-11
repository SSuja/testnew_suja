package com.tokyo.supermix.data.entities.privilege;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "role_mainroute")
public class RoleMainRoutePrivilege {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "main_route_id")
  private Long mainRouteId;
  @ManyToOne
  @JoinColumn(name = "role_id") 
  private Long roleId;
  private Boolean status;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public Long getMainRouteId() {
    return mainRouteId;
  }
  public void setMainRouteId(Long mainRouteId) {
    this.mainRouteId = mainRouteId;
  }
  public Long getRoleId() {
    return roleId;
  }
  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }
  public Boolean getStatus() {
    return status;
  }
  public void setStatus(Boolean status) {
    this.status = status;
  }
}
