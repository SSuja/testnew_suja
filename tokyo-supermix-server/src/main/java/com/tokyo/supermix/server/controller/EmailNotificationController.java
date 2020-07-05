package com.tokyo.supermix.server.controller;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.tokyo.supermix.server.services.GenerateReportService;
import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin
public class EmailNotificationController {
	@Autowired
	private GenerateReportService generateReportService;

	@GetMapping("/report/{incomingSampleCode}")
	public String generateSummaryReport(@PathVariable String incomingSampleCode)
			throws FileNotFoundException, JRException, MessagingException {
		return generateReportService.generatePdfSummaryDetailReport(incomingSampleCode);
	}
	@GetMapping("/report/{incomingSampleCode}/test-name/{testName}")
	public String generateDeliveryReport(@PathVariable String incomingSampleCode,@PathVariable String testName)
			throws FileNotFoundException, JRException, MessagingException {
		return generateReportService.generatePdfDeliveryDetailReport(incomingSampleCode, testName);
	}
}
