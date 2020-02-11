package com.tokyo.supermix.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "tokyo-supermix", name = "suppiler")
public class Supplier {

	private static final long serialVersionUID = 1L;

	@Id
	private String code;

	private String name;

	private String companyName;

	private String address;

	private String phoneNumber;

	private String email;

	@ManyToOne
	@JoinColumn(name = "suppilerCategoryCode", nullable = false)
	private SupplierCategory suppilerCategory;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SupplierCategory getSuppilerCategory() {
		return suppilerCategory;
	}

	public void setSuppilerCategory(SupplierCategory suppilerCategory) {
		this.suppilerCategory = suppilerCategory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
