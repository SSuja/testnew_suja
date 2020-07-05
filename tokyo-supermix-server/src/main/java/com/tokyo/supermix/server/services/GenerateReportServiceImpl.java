package com.tokyo.supermix.server.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import com.tokyo.supermix.data.dto.report.IncomingSampleDeliveryReportDto;
import com.tokyo.supermix.data.dto.report.IncomingSampleTestDto;
import com.tokyo.supermix.util.Constants;
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
		IncomingSampleDeliveryReportDto deliveryReport = testReportService
				.getIncomingSampleSummaryReport(incomingSampleCode);
		List<IncomingSampleDeliveryReportDto> deliveryReports = new ArrayList<IncomingSampleDeliveryReportDto>();
		deliveryReports.add(deliveryReport);
		List<IncomingSampleTestDto> incomingSampleTestDtos = deliveryReport.getIncomingSampleTestDtos();
		File file = ResourceUtils.getFile("classpath:report.jrxml");
		String tempPath = file.getAbsolutePath();
		Map<String, Object> params = new HashMap<>();
		params.put("datasource1", deliveryReports);
		params.put("datasource2", incomingSampleTestDtos);
		byte[] fileByte = generateReportPdf(tempPath, params);
		List<String> reciepientList = emailRecipientService.getEmailsByEmailGroupNameAndPlantCode(
		        "Incoming Sample Group", deliveryReport.getPlant().getCode());
		emailService.sendEmailWithAttachment(reciepientList.toArray(new String[reciepientList.size()]), Constants.SUBJECT_REPORT,
				Constants.BODY_FOR_REPORT, fileByte, Constants.SUMMARY_REPORT);
	}
	
	private byte[] generateReportPdf(String tempPath, Map<String, Object> params)
			throws FileNotFoundException, JRException, MessagingException {
		JasperReport jasperReport = JasperCompileManager.compileReport(tempPath);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}
	
	@Override
	public void generatePdfDeliveryDetailReport(String incomingSampleCode, String testName)
			throws FileNotFoundException, JRException, MessagingException {
		IncomingSampleDeliveryReportDto deliveryReport = testReportService
				.getIncomingSampleDeliveryReports(incomingSampleCode, testName);
		List<IncomingSampleDeliveryReportDto> deliveryReports = new ArrayList<IncomingSampleDeliveryReportDto>();
		deliveryReports.add(deliveryReport);
		List<IncomingSampleTestDto> incomingSampleTestDtos = deliveryReport.getIncomingSampleTestDtos();
		File file = ResourceUtils.getFile("classpath:report.jrxml");
		String tempPath = file.getAbsolutePath();
		Map<String, Object> params = new HashMap<>();
		params.put("datasource1", deliveryReports);
		params.put("datasource2", incomingSampleTestDtos);
		byte[] fileByte = generateReportPdf(tempPath, params);
		List<String> reciepientList = emailRecipientService.getEmailsByEmailGroupNameAndPlantCode(
		        "Incoming Sample Group", deliveryReport.getPlant().getCode());
		emailService.sendEmailWithAttachment(reciepientList.toArray(new String[reciepientList.size()]), Constants.SUBJECT_REPORT,
				Constants.BODY_FOR_REPORT, fileByte,Constants.DELIVERY_REPORT );
	}
}
