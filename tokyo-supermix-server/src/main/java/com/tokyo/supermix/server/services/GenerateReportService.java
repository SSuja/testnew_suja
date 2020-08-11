package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;

import javax.mail.MessagingException;
import net.sf.jasperreports.engine.JRException;

public interface GenerateReportService {
  public void generatePdfSummaryDetailReport(String incomingSampleCode)
      throws FileNotFoundException, JRException, MessagingException;

  public void generatePdfDeliveryDetailReport(String incomingSampleCode, String testName,
      String plantCode) throws FileNotFoundException, JRException, MessagingException;
}
