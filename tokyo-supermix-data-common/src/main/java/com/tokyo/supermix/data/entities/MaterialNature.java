package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material_nature")
public class MaterialNature {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long code;

	private String materialNature;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMaterialNature() {
		return materialNature;
	}

	public void setMaterialNature(String materialNature) {
		this.materialNature = materialNature;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
