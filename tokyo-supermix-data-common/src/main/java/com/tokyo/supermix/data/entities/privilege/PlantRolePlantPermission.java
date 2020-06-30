package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plant_role_plant_permission")
public class PlantRolePlantPermission implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "plant_permission_id")
  private PlantPermission plantPermission;
  @ManyToOne
  @JoinColumn(name = "plant_role_id")
  private PlantRole plantRole;
  private Boolean status;

  public PlantPermission getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(PlantPermission plantPermission) {
    this.plantPermission = plantPermission;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PlantRole getPlantRole() {
    return plantRole;
  }

  public void setPlantRole(PlantRole plantRole) {
    this.plantRole = plantRole;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}