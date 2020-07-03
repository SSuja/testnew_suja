package com.tokyo.supermix.notification;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.server.services.EmailNotificationDaysService;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Configuration
public class EmailNotification {
  @Autowired
  private EmailService emailService;
  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private FinishProductSampleRepository finishProductSampleRepository;
  @Autowired
  private EmailRecipientService emailRecipientService;
  @Autowired
  private EmailNotificationDaysService emailNotificationDaysService;
  
  @Scheduled(cron = "${mail.notificationtime.plantEquipment}")
  public void alertForEquipmentCalibration() {
    final LocalDateTime today = LocalDateTime.now();
    plantEquipmentCalibrationRepository.findAll().forEach(calibration -> {
      long noOfDays =
          ChronoUnit.DAYS.between(today.toLocalDate(), calibration.getDueDate().toLocalDate());
      List<NotificationDays> notificationDaysList = emailNotificationDaysService.getByEmailGroupName(Constants.EMAIL_GROUP_PLANT_EQUIPMENT_CALIBRATION);     
      notificationDaysList.forEach(notificationday -> {
        if (noOfDays == notificationday.getDays()) {
          sendEquipmentMail(calibration);
        }
      });   
    });
  }

  private void sendEquipmentMail(PlantEquipmentCalibration calibration ) {
    List<String>  equipmentCalibrationEmailList = emailRecipientService.getEmailsByEmailGroupNameAndPlantCode(Constants.EMAIL_GROUP_PLANT_EQUIPMENT_CALIBRATION, calibration.getPlantEquipment().getPlant().getCode()); 
    emailService.sendMail(equipmentCalibrationEmailList.toArray(new String[equipmentCalibrationEmailList.size()]),
        Constants.SUBJECT_EQUIPMENT_CALIBRATION, "Please Calibrate the " + calibration.getPlantEquipment().getEquipment().getName()
            + " due date is " + calibration.getDueDate().toLocalDate() + ". Plant name is "
            + calibration.getPlantEquipment().getPlant().getName());
  }

//  @Scheduled(cron = "0 0 5 * * ?")
//  public void notifyTheIncomingSamplePerDay() {
//    final LocalDateTime today = LocalDateTime.now();
//    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());
//    rawMaterialRepository.findAll().forEach(material -> {
//      int noOfSamples = incomingSampleRepository
//          .findByStatusAndRawMaterialIdAndDate(Status.NEW, material.getId(), sqlDate).size();
//      if (noOfSamples > 0) {
//        emailService.sendMail(mailConstants.getMailIncomingSamplePerDay(),
//            Constants.SUBJECT_INCOMING_SAMPLES_PER_DAY, "Today " + noOfSamples
//                + " Samples Arrived for the Raw Material : " + material.getName());
//      }
//    });
//  }

  @Scheduled(cron = "${mail.notificationtime.strengthTestMixDesign}")
  public void notifyStrengthTestForMixdesign() {
    final LocalDateTime today = LocalDateTime.now();
    finishProductSampleRepository.findAll().forEach(finishProductSample -> {
      long noOfDays =
          ChronoUnit.DAYS.between(finishProductSample.getDate().toLocalDate(), today.toLocalDate());
      List<NotificationDays> notificationDaysList = emailNotificationDaysService.getByEmailGroupName(Constants.EMAIL_GROUP_MIX_DESIGN);
      notificationDaysList.forEach(notificationday -> {
        if (noOfDays == notificationday.getDays()) {
          sendMixDesignEmail(finishProductSample, noOfDays);
        }
      });
      
    });
  }

  private void sendMixDesignEmail(FinishProductSample finishProductSample, long noOfDays) {
    String mailBody = "The work order no is " + finishProductSample.getWorkOrderNo()
    + ", created on " + finishProductSample.getDate() + "."  + " reached "+noOfDays + "days "+
     " Please test" +noOfDays+" days strength.";
List<String> reciepientList = emailRecipientService.getEmailsByEmailGroupNameAndPlantCode("Mixdesign Group",
    finishProductSample.getMixDesign().getPlant().getCode());
emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
    Constants.SUBJECT_MIX_DESIGN, mailBody);
    
  }

//  @Scheduled(cron = "0 0 8 * * ?")
//  public void notifyPendingIncomingSample() {
//    final LocalDateTime today = LocalDateTime.now();
//    final LocalDateTime incomingDate = today.minusDays(3L);
//    System.out.println(incomingDate);
//    java.sql.Date sqlDate = java.sql.Date.valueOf(incomingDate.toLocalDate());
//    rawMaterialRepository.findAll().forEach(material -> {
//      incomingSampleRepository.findByRawMaterialIdAndDate(material.getId(), sqlDate)
//          .forEach(incomingSample -> {
//            if (incomingSample.getStatus().equals(Status.NEW)) {
//              emailService.sendMail(mailConstants.getMailPendingIncomingSample(),
//                  Constants.SUBJECT_PENDING_SAMPLES,
//                  "This incoming " + material.getName() + " sample didn't start the test from "
//                      + incomingSample.getDate() + " .the sample code is "
//                      + incomingSample.getCode());
//            }
//            if (incomingSample.getStatus().equals(Status.PROCESS)) {
//              emailService.sendMail(mailConstants.getMailPendingIncomingSample(),
//                  Constants.SUBJECT_PENDING_SAMPLES,
//                  "This incoming " + material.getName() + " sample didn't complete the test from "
//                      + new SimpleDateFormat(incomingSample.getDate().toString())
//                      + " .the sample code is " + incomingSample.getCode());
//            }
//          });
//    });
//  }
}
