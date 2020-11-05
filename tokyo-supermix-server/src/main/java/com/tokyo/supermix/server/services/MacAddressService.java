package com.tokyo.supermix.server.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tokyo.supermix.data.entities.MacAddress;

public interface MacAddressService {

  public void saveMacAddress(MacAddress macAddress);

  public boolean isMacAddressExist(Long id);

  public boolean isMacAddressExist(String macAddress);

  public List<MacAddress> getAllMacAddress();

  public void deleteMacAddress(Long id);

  public MacAddress getMacAddressById(Long id);

  public String getClientMACAddress(HttpServletRequest request);


}
