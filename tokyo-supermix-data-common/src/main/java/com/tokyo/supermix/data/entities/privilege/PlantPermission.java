package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tokyo.supermix.data.entities.Plant;

@Entity
@Table(name = "plant_permission")
public class PlantPermission implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private Long id;
  @ManyToOne
  @JoinColumn(name = "permission_id")
  private Permission permission;
  @ManyToOne
  @JoinColumn(name = "plant_code")
  @OneToMany(mappedBy = "plantpermission")
  private Set<RolePlantPermission> rolePlantPermission;

  private Plant plant;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Permission getPermission() {
    return permission;
  }

  public void setPermission(Permission permission) {
    this.permission = permission;
  }

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public Set<RolePlantPermission> getRolePlantPermission() {
    return rolePlantPermission;
  }

  public void setRolePlantPermission(Set<RolePlantPermission> rolePlantPermission) {
    this.rolePlantPermission = rolePlantPermission;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
