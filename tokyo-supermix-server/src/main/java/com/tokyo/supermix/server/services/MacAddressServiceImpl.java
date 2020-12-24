package com.tokyo.supermix.server.services;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.MacAddress;
import com.tokyo.supermix.data.repositories.MacAddressRepository;
import com.tokyo.supermix.server.controller.EmployeeController;



@Service
public class MacAddressServiceImpl implements MacAddressService {

  @Autowired
  MacAddressRepository macAddressRepository;
  private static final Logger logger = Logger.getLogger(EmployeeController.class);

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

  public String getClientMACAddress() {
    InetAddress ip;
    StringBuilder sb = new StringBuilder();
    try {
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface network = networkInterfaces.nextElement();
        byte[] mac = network.getHardwareAddress();
        if (mac == null) {
          logger.debug(getClientMACAddress(), null);
        } else {
            for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
          }
          break;
        }
      }
      ip = InetAddress.getLocalHost();
    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  @Transactional(readOnly = true)
  public boolean isMacAddressExist(Long id) {
    return macAddressRepository.existsById(id);
  }
}
