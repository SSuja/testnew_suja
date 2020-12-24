package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MacAddressDto {
	private Long id;
	@NotNull(message = "{macAddressDto.macAddress.null}")
    @NotEmpty(message = "{macAddressDto.macAddress.empty}")
	private String macAddress;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

}
