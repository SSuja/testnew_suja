package com.tokyo.supermix.data.entities.privilege;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
    private RolePermissionPK id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("role_id") 
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("permission_id")
    @JoinColumn(name = "PERMISSION_ID")
    private Permission permission;   
    private boolean status;
    
	public RolePermissionPK getId() {
		return id;
	}
	public void setId(RolePermissionPK id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Permission getPermission() {
			return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
    
}
