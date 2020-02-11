package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "main_category")
public class MainCategory {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String name;

	@ManyToOne
	@JoinColumn(name = "materialTypeCode", nullable = false)
	private MaterialType materialType;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
