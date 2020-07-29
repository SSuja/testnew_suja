package com.tokyo.supermix.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.EndpointURI;

@RestController
@CrossOrigin
public class SessionController {
  @GetMapping(value =  EndpointURI.PLANT_SESSION)
  public String getSession(@PathVariable String plantCode, HttpServletRequest request) {
    request.getSession().setAttribute("MY_SESSION_PLANT", plantCode);
    return plantCode;
  }
  @GetMapping("/sessionGet")
  public String getSessionCode(HttpSession session) {
    String currentPlantCode = (String)session.getAttribute("MY_SESSION_PLANT");
    return "return value from session "+currentPlantCode;
  }
}
