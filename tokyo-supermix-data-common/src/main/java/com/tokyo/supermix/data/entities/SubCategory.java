package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "sub_category")
public class SubCategory {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String name;

	@ManyToOne
	@JoinColumn(name = "mainCategoryCode", nullable = false)
	private MainCategory mainCategory;

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

	public MainCategory getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(MainCategory mainCategory) {
		this.mainCategory = mainCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
