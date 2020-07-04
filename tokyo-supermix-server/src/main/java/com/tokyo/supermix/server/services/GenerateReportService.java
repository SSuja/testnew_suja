package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JRException;

public interface GenerateReportService {
	public String generatePdfSummaryDetailReport(String materialTestCode) throws FileNotFoundException, JRException, MessagingException;
}
