package com.tokyo.supermix.querydsl.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.querydsl.service.MyEmployeePredicatesBuilder;
import com.tokyo.supermix.querydsl.service.MyEmployeeRepository;
import com.tokyo.supermix.querydsl.service.EmployeeQuerydslService;



@RestController
public class EmployeeSearchController {
  @Autowired
  private MyEmployeeRepository myEmployeeRepository;
  @Autowired
  private EmployeeQuerydslService service;

  @RequestMapping(method = RequestMethod.GET, value = "/myusers")
  @ResponseBody
  public Iterable<Employee> findAllByQuerydsl(@RequestParam(value = "search") String search) {
    MyEmployeePredicatesBuilder builder = new MyEmployeePredicatesBuilder();
    if (search != null) {
      Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
      Matcher matcher = pattern.matcher(search + ",");
      while (matcher.find()) {
        builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
      }
    }
    BooleanExpression exp = builder.build();
    return myEmployeeRepository.findAll(exp);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/objects/{plantCode}")
  @ResponseBody
  public List<Employee> searchPlant(@PathVariable String plantCode) {
    return service.findEmployeesByPlantCodeQueryDSL(plantCode);
  }
}
