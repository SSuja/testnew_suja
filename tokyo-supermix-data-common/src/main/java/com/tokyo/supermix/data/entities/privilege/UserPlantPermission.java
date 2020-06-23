package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tokyo.supermix.data.entities.auth.User;

@Entity
@Table(name = "user_plant_permission")
public class UserPlantPermission implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "plant_permission_id")
  private PlantPermission plantPermission;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private Boolean status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PlantPermission getPlantPermission() {
    return plantPermission;
  }

  public void setPlantPermission(PlantPermission plantPermission) {
    this.plantPermission = plantPermission;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
   public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

}
