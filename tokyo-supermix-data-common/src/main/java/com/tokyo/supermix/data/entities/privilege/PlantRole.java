package com.tokyo.supermix.data.entities.privilege;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.auth.Role;

@Entity
public class PlantRole {
	@Id
    Long id;
 
    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;
 
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
