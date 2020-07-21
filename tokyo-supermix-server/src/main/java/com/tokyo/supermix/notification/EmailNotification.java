package com.tokyo.supermix.notification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.server.services.EmailNotificationDaysService;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.data.entities.NotificationDays;
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
  private MailGroupConstance mailGroupConstance;
  @Autowired
  private EmailGroupRepository emailGroupRepository;

  @Scheduled(cron = "${mail.notificationtime.plantEquipment}")
  public void alertForEquipmentCalibration() {
    final LocalDateTime today = LocalDateTime.now();
    plantEquipmentCalibrationRepository.findAll().forEach(calibration -> {
      long noOfDays =
          ChronoUnit.DAYS.between(today.toLocalDate(), calibration.getDueDate().toLocalDate());
     String plantCode = plantEquipmentCalibrationRepository.findById(calibration.getId()).get().getPlantEquipment().getPlant().getCode();
      List<NotificationDays> notificationDaysList = emailNotificationDaysService.getByEmailGroup(Constants.PLANT_EQUIPMENT_CALIBRATION_GROUP, plantCode);
      notificationDaysList.forEach(notificationday -> {
        if (noOfDays == notificationday.getDays()) {
          sendEquipmentMail(calibration);
        }
      });
    });
  }

  private void sendEquipmentMail(PlantEquipmentCalibration calibration) {
    List<String> equipmentCalibrationEmailList = emailRecipientService
        .getEmailsByEmailNotificationAndPlantCode(Constants.PLANT_EQUIPMENT_CALIBRATION_GROUP,
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
      // List<NotificationDays> notificationDaysList =
      // emailNotificationDaysService.getByEmailGroup(EmailNotifications.MIX_DESIGN_GROUP);
      // notificationDaysList.forEach(notificationday -> {
      // if (noOfDays == notificationday.getDays()) {
      // sendMixDesignEmail(finishProductSample, noOfDays);
      // }
      // });
    });
  }

  private void sendMixDesignEmail(FinishProductSample finishProductSample, long noOfDays) {
    String mailBody = "The work order no is " + finishProductSample.getWorkOrderNo()
        + ", created on " + finishProductSample.getDate() + "." + " reached " + noOfDays + "days "
        + " Please test" + noOfDays + " days strength.";
    // List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
    // EmailNotifications.MIX_DESIGN_GROUP,
    // finishProductSample.getMixDesign().getPlant().getCode());
    // emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
    // Constants.SUBJECT_MIX_DESIGN, mailBody);
  }

  public void sendProjectEmail(Project project) {
    String mailBody = "project";
    EmailGroup emailGroup =emailGroupRepository.findByPlantCodeAndEmailPointsName(project.getPlant().getCode(), mailGroupConstance.CREATE_PROJECT);
    if(emailGroup!=null) {
      if(emailGroup.isStatus()) {
      List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
          mailGroupConstance.CREATE_PROJECT, project.getPlant().getCode());
      
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_NEW_PROJECT, mailBody);
      }
    }
  }
}


