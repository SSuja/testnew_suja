package com.tokyo.supermix.server.services;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleTestDto;
import com.tokyo.supermix.data.enums.ReportFormat;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailGroupConstance;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class GenerateReportServiceImpl implements GenerateReportService {
  @Autowired
  private TestReportService testReportService;
  @Autowired
  private EmailService emailService;
  @Autowired
  private EmailRecipientService emailRecipientService;

  @Override
  public void generatePdfSummaryDetailReport(String incomingSampleCode)
      throws FileNotFoundException, JRException, MessagingException {
    IncomingSampleDeliveryReportDto deliveryReport =
        testReportService.getIncomingSampleSummaryReport(incomingSampleCode);
    List<IncomingSampleDeliveryReportDto> deliveryReports =
        new ArrayList<IncomingSampleDeliveryReportDto>();
    deliveryReports.add(deliveryReport);
    List<IncomingSampleTestDto> incomingSampleTestDtos = deliveryReport.getIncomingSampleTestDtos();
    Map<String, Object> params = new HashMap<>();
    params.put("datasource1", deliveryReports);
    params.put("datasource2", incomingSampleTestDtos);
    params.put("reportTitle", "SUMMARY DETAILS REPORT OF INCOMING SAMPLE");
    InputStream inputStream =
        getClass().getClassLoader().getResourceAsStream("summary_report.jrxml");
    byte[] fileByte = generateReportPdf(inputStream, params);
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        MailGroupConstance.CREATE_SUMMARY_REPORT, deliveryReport.getPlant().getCode());
    emailService.sendEmailWithAttachment(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_OF_SUMMARY_REPORT, Constants.BODY_FOR_REPORT, fileByte,
        Constants.SUMMARY_REPORT);
  }

  private byte[] generateReportPdf(InputStream inputStream, Map<String, Object> params)
      throws FileNotFoundException, JRException, MessagingException {
    JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
    JasperPrint jasperPrint =
        JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
    return JasperExportManager.exportReportToPdf(jasperPrint);
  }

  @Override
  public void generatePdfDeliveryDetailReport(String incomingSampleCode) throws FileNotFoundException, JRException, MessagingException {
    IncomingSampleDeliveryReportDto deliveryReport = testReportService
        .getIncomingSampleDeliveryReports(incomingSampleCode,ReportFormat.DELIVERY_REPORT);
    List<IncomingSampleDeliveryReportDto> deliveryReports =
        new ArrayList<IncomingSampleDeliveryReportDto>();
    deliveryReports.add(deliveryReport);
    List<IncomingSampleTestDto> incomingSampleTestDtos = deliveryReport.getIncomingSampleTestDtos();
    Map<String, Object> params = new HashMap<>();
    params.put("datasource1", deliveryReports);
    params.put("datasource2", incomingSampleTestDtos);
    params.put("reportTitle", "DELIVERY DETAILS REPORT OF INCOMING SAMPLE");
    InputStream inputStream =
        getClass().getClassLoader().getResourceAsStream("delivery_report.jrxml");
    byte[] fileByte = generateReportPdf(inputStream, params);
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        MailGroupConstance.CREATE_DELIVERY_REPORT, deliveryReport.getPlant().getCode());
    reciepientList.add(deliveryReport.getSupplierReportDtos().getEmail());
    emailService.sendEmailWithAttachment(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_OF_DELIVERY_REPORT, Constants.BODY_FOR_REPORT, fileByte,
        Constants.DELIVERY_REPORT);
  }
}
