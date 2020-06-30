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
  @JoinColumn(name = "sub_module_id", nullable = false)
  private SubModule subModule;
  @OneToMany(mappedBy = "permission")
  private Set<PlantPermission> plantPermission;

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

  public SubModule getSubModule() {
    return subModule;
  }

  public void setSubModule(SubModule subModule) {
    this.subModule = subModule;
  }

  public Set<PlantPermission> getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(Set<PlantPermission> plantPermission) {
    this.plantPermission = plantPermission;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
