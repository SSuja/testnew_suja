package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;

@RestController
@CrossOrigin
public class SessionController {
  @Autowired
  HttpSession session;
  @GetMapping(value =  EndpointURI.PLANT_SESSION)
  public String getSession(@PathVariable String plantCode, HttpServletRequest request) {
    request.getSession().setAttribute("MY_SESSION_PLANT", plantCode);
    System.out.println("session is " + plantCode);
    return (String)session.getAttribute("MY_SESSION_PLANT");
  }
  @GetMapping(value =  EndpointURI.PLANT_SESSION_GET)
  public String getSessionCode(HttpSession session) {
    String currentPlantCode = (String)session.getAttribute("MY_SESSION_PLANT");
    return "return value from session "+currentPlantCode;
  }
}
