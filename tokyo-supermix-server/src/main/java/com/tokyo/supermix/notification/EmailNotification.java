package com.tokyo.supermix.notification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.server.services.EmailNotificationDaysService;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.util.MailGroupConstance;

@Configuration
public class EmailNotification {
  @Autowired
  private EmailService emailService;
  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private EmailRecipientService emailRecipientService;
  @Autowired
  private EmailNotificationDaysService emailNotificationDaysService;
  @Autowired
  private EmailGroupRepository emailGroupRepository;
  @Autowired
  private EmailPointsRepository emailPointsRepository;

  @Scheduled(cron = "${mail.notificationtime.plantEquipment}")
  public void alertForEquipmentCalibration() {
    final LocalDateTime today = LocalDateTime.now();
    plantEquipmentCalibrationRepository.findAll().forEach(calibration -> {
      long noOfDays =
          ChronoUnit.DAYS.between(today.toLocalDate(), calibration.getDueDate().toLocalDate());
      String plantCode = plantEquipmentCalibrationRepository.findById(calibration.getId()).get()
          .getPlantEquipment().getPlant().getCode();
      List<NotificationDays> notificationDaysList = emailNotificationDaysService
          .getByEmailGroup(MailGroupConstance.PLANT_EQUIPMENT_CALIBRATION_GROUP, plantCode);
      notificationDaysList.forEach(notificationday -> {
        if (noOfDays == notificationday.getDays()) {
          sendEquipmentMail(calibration);
        }
      });
    });
  }

  private void sendEquipmentMail(PlantEquipmentCalibration calibration) {
    List<String> equipmentCalibrationEmailList =
        emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
            MailGroupConstance.PLANT_EQUIPMENT_CALIBRATION_GROUP,
            calibration.getPlantEquipment().getPlant().getCode());
    emailService.sendMail(
        equipmentCalibrationEmailList.toArray(new String[equipmentCalibrationEmailList.size()]),
        Constants.SUBJECT_EQUIPMENT_CALIBRATION,
        "Please Calibrate the " + calibration.getPlantEquipment().getEquipment().getName()
            + " due date is " + calibration.getDueDate().toLocalDate() + ". Plant name is "
            + calibration.getPlantEquipment().getPlant().getName());
  }

  @Scheduled(cron = "${mail.notificationtime.strengthTestMixDesign}")
  public void notifyStrengthTestForMixdesign() {
    final LocalDateTime today = LocalDateTime.now();
    finishProductSampleRepository.findAll().forEach(finishProductSample -> {
      long noOfDays = ChronoUnit.DAYS.between(
          finishProductSample.getCreatedAt().toLocalDateTime().toLocalDate(), today.toLocalDate());
      String plantCode = finishProductSampleRepository.findById(finishProductSample.getCode()).get()
          .getMixDesign().getPlant().getCode();
      List<NotificationDays> notificationDaysList = emailNotificationDaysService
          .getByEmailGroup(MailGroupConstance.MIX_DESIGN_EMAIL_GROUP, plantCode);
      notificationDaysList.forEach(notificationday -> {
        if (noOfDays == notificationday.getDays()) {
          sendMixDesignEmail(finishProductSample, noOfDays);
        }
      });
    });
  }

  private void sendMixDesignEmail(FinishProductSample finishProductSample, long noOfDays) {
    String mailBody = "The work order no is " + finishProductSample.getWorkOrderNo()
        + ", created on " + finishProductSample.getDate() + "." + " reached " + noOfDays + "days "
        + " Please test" + noOfDays + " days strength.";
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        MailGroupConstance.MIX_DESIGN_EMAIL_GROUP,
        finishProductSample.getMixDesign().getPlant().getCode());
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_MIX_DESIGN, mailBody);
  }

  public void sendProjectEmail(Project project) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        project.getPlant().getCode(), MailGroupConstance.CREATE_PROJECT);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_PROJECT, mailBody);
      }
    }
  }

  public void sendTestEmail(MaterialTest materialTest) {
    EmailPoints emailPoints = emailPointsRepository.findByMaterialSubCategoryIdAndTestId(
        materialTest.getTestConfigure().getMaterialSubCategory().getId(),
        materialTest.getTestConfigure().getTest().getId());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        materialTest.getIncomingSample().getPlant().getCode(), emailPoints.getName());
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        String mailBody = "<ul><li>Incoming Sample <b>" + materialTest.getIncomingSample().getCode()
            + "</b></li><li> Test Name <b>" + materialTest.getTestConfigure().getTest().getName()
            + "</b></li><li> Material <b>" + materialTest.getTestConfigure().getTest().getName()
            + "</b></li><li> Supplier <b>"
            + materialTest.getIncomingSample().getSupplier().getName()
            + "</b></li><li> Test Results <b>" + materialTest.getAverage()
            + "</b></li><li> Status <b>" + materialTest.getStatus() + "</b></li></ul>";
      
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_MATRIAL_TEST, mailBody);
      }
    }
  }

  public void sendSupplierEmail(Supplier supplier) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        supplier.getPlant().getCode(), MailGroupConstance.CREATE_SUPPLIER);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_SUPPLIER, mailBody);
      }
    }
  }

  public void sendEmployeeEmail(Employee employee) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        employee.getPlant().getCode(), MailGroupConstance.CREATE_EMPLOYEE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_EMPLOYEE, mailBody);
      }
    }
  }

  public void sendIncomingSampleEmail(IncomingSample incomingSample) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        incomingSample.getPlant().getCode(), MailGroupConstance.CREATE_INCOMING_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_INCOMING_SAMPLE, mailBody);
      }
    }
  }

  public void sendFinishProductSampleEmail(FinishProductSample finishProductSample) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductSample.getMixDesign().getPlant().getCode(),
        MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE, mailBody);
      }
    }
  }

  public void sendFinishProductSampleIssueEmail(FinishProductSampleIssue finishProductSampleIssue) {
    String mailBody = "project";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductSampleIssue.getFinishProductSample().getMixDesign().getPlant().getCode(),
        MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE_ISSUE_);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE_ISSUE, mailBody);
      }
    }
  }

  public void sendPlantEquipmentCalibrationEmail(PlantEquipment plantequipment) {
    String mailBody = "Plant Equipment Calibration Created Successfully for the plant,"
        + plantequipment.getPlant().getCode() + ".";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        plantequipment.getPlant().getCode(), MailGroupConstance.CREATE_PLANT_EQUIPMENT);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_PLANT_EQUIPMENT, plantequipment.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PLANT_EQUIPMENT, mailBody);
      }
    }
  }

  public void sendRawmaterialCreationEmail(RawMaterial rawMaterial) {
    // String mailBody = "Raw material "+rawMaterial.getName() +"created for material sub category"
    // + rawMaterial.getMaterialSubCategory().getName()+".";
    // EmailGroup emailGroup
    // =emailGroupRepository.findByPlantCodeAndEmailPointsName(plantequipment.getPlant().getCode(),
    // MailGroupConstance.CREATE_PLANT_EQUIPMENT);
    // if(emailGroup!=null) {
    // if(emailGroup.isStatus()) {
    // List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
    // MailGroupConstance.CREATE_PLANT_EQUIPMENT, plantequipment.getPlant().getCode());
    //
    // emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
    // Constants.SUBJECT_PLANT_EQUIPMENT, mailBody);
    // }
    // }
  }

  public void sendCustomerCreationEmail(Customer customer) {
    String mailBody = "Customer, " + customer.getName() + " having email id " + customer.getEmail()
        + " created successfully";
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        customer.getPlant().getCode(), MailGroupConstance.CREATE_CUSTOMER);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_CUSTOMER, customer.getPlant().getCode());

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PLANT_EQUIPMENT, mailBody);
      }
    }
  }
}


