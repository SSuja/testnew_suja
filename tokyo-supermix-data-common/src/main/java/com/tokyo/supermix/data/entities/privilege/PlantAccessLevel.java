package com.tokyo.supermix.data.entities.privilege;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.tokyo.supermix.data.entities.Plant;

@Entity
@Table(name = "plant_access_level")
public class PlantAccessLevel {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "plant_role_id")
  private PlantRole plantRole;
  @ManyToOne
  @JoinColumn(name = "plant_code")
  private Plant plant;
  private boolean status;

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

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
