package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JRException;

public interface GenerateReportService {
	public String generatePdfSummaryDetailReport(String incomingSampleCode) throws FileNotFoundException, JRException, MessagingException;
	public String generatePdfDeliveryDetailReport(String incomingSampleCode, String testName) throws FileNotFoundException, JRException, MessagingException;
}
