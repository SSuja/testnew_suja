package com.tokyo.supermix.server.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MacAddress;
import com.tokyo.supermix.data.repositories.MacAddressRepository;

@Service
public class MacAddressServiceImpl implements MacAddressService {

	@Autowired
	MacAddressRepository macAddressRepository;

	 @Transactional
	public void saveMacAddress(MacAddress macAddress) {
		macAddressRepository.save(macAddress);
	}

	 @Transactional(readOnly = true)
	public boolean isMacAddressExist(String macAddress) {
		return macAddressRepository.existsByMacAddress(macAddress);
	}

	@Transactional(readOnly = true)
	public List<MacAddress> getAllMacAddress() {
		return macAddressRepository.findAll();
	}

	@Transactional(propagation = Propagation.NEVER)
	public void deleteMacAddress(Long id) {
		macAddressRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public MacAddress getMacAddressById(Long id) {
		return macAddressRepository.findById(id).get();
	}
	
	public String getClientMACAddress(HttpServletRequest request){ 
	     String str = ""; 
	     String macAddress = ""; 
	     try { 
	          Process p = Runtime.getRuntime().exec("nbtstat -A " + request.getRemoteAddr()); 
	          InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
	          LineNumberReader input = new LineNumberReader(ir); 
	          for (int i = 1; i <100; i++) { 
	               str = input.readLine(); 
	               if (str != null) { 
	                    if (str.indexOf("MAC Address") > 1) { 
	                         macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
	                         break; 
	                    } 
	               } 
	          } 
	     } catch (IOException e) { 
	          e.printStackTrace(System.out); 
	     } 
	     return macAddress; 
	}

	@Transactional(readOnly = true)
	public boolean isMacAddressExist(Long id) {
		return macAddressRepository.existsById(id);
	}
 

}
