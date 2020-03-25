package com.tokyo.supermix.server.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.server.services.EmailService;

@RestController
public class EmailTestController {
	@Autowired
	private EmailService emailService;

	@GetMapping("/api/v1/mail")
	ResponseEntity<Object> sendMail() {
		emailService.sendMail("user@gmail.com", "System Message", "Testing for e-mail notification");
		return new ResponseEntity<>("Sucessfully Send the mail!!", HttpStatus.OK);
	}
}
