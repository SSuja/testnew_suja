package com.tokyo.supermix.data.dto.privilege;

import java.util.List;

public class SubModuleRolePermissionDto {
	private Long subModuleId;
	private Long mainModuleId;
	private String subModule;
	private boolean status;
	private List<RolePermissionRequestDto> privilages;

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public List<RolePermissionRequestDto> getRolePermissions() {
		return privilages;
	}

	public void setRolePermissions(List<RolePermissionRequestDto> rolePermissions) {
		this.privilages = rolePermissions;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Long getSubModuleId() {
		return subModuleId;
	}

	public void setSubModuleId(Long subModuleId) {
		this.subModuleId = subModuleId;
	}

	public Long getMainModuleId() {
		return mainModuleId;
	}

	public void setMainModuleId(Long mainModuleId) {
		this.mainModuleId = mainModuleId;
	}

	public List<RolePermissionRequestDto> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(List<RolePermissionRequestDto> privilages) {
		this.privilages = privilages;
	}

}
