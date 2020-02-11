package com.tokyo.supermix.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "material")
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;
	private String name;
	@ManyToOne
	@JoinColumn(name = "subCategoryId", nullable = false)
	private SubCategory subCategory;

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

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
