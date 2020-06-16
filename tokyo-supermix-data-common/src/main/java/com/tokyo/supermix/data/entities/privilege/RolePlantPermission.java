package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role_plant_permission")
public class RolePlantPermission implements Serializable {
	 private static final long serialVersionUID = 1L;
		@Id
	    private Long id;
		@ManyToOne
	    @JoinColumn(name = "plantPermission_id")
	    private PlantPermission plantpermission;      
	    @ManyToOne
	    @JoinColumn(name = "plantRole_id")
	    private PlantRole plantRole;
	    private boolean status;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public PlantPermission getPlantpermission() {
			return plantpermission;
		}

		public void setPlantpermission(PlantPermission plantpermission) {
			this.plantpermission = plantpermission;
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

		public void setStatus(boolean status) {
			this.status = status;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    

}
