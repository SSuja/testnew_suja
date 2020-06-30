package com.tokyo.supermix.notification;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailConstants;

@Configuration
public class EmailNotification {
  @Autowired
  private EmailService emailService;
  @Autowired
  private MailConstants mailConstants;
  @Autowired
  private PlantEquipmentCalibrationRepository plantEquipmentCalibrationRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private EmailRecipientService emailRecipientService;

  @Scheduled(cron = "0 0 9 * * ?")
  public void alertForEquipmentCalibration() {
    final LocalDateTime today = LocalDateTime.now();
    plantEquipmentCalibrationRepository.findAll().forEach(calibration -> {
      long noOfDays =
          ChronoUnit.DAYS.between(today.toLocalDate(), calibration.getDueDate().toLocalDate());
      if (noOfDays == 30 || noOfDays == 15) {
        List<String>  equipmentCalibrationEmailList = emailRecipientService.getEmailsByEmailGroupNameAndPlantCode(Constants.EMAIL_GROUP_PLANT_EQUIPMENT_CALIBRATION, calibration.getPlantEquipment().getPlant().getCode());    
        emailService.sendMail(equipmentCalibrationEmailList.toArray(new String[equipmentCalibrationEmailList.size()]),
            Constants.SUBJECT_EQUIPMENT_CALIBRATION, "Please Calibrate the " + calibration.getPlantEquipment().getEquipment().getName()
                + " due date is " + calibration.getDueDate().toLocalDate() + ". Plant name is "
                + calibration.getPlantEquipment().getPlant().getName());
      }
    });
  }

//  @Scheduled(cron = "0 0 8 * * ?")
//  public void notifyTheExpiryDateForAdmixure() {
//    final LocalDateTime today = LocalDateTime.now();
//    processSampleLoadRepository.findAll().forEach(processsampleLoad -> {
//      if (processsampleLoad.getProcessSample().getIncomingSample().getRawMaterial()
//          .getMaterialSubCategory().getMaterialCategory().getName()
//          .equalsIgnoreCase(Constants.ADMIXTURE)) {
//        long noOfDays = ChronoUnit.DAYS.between(today.toLocalDate(),
//            processsampleLoad.getExpiryDate().toLocalDate());
//        if (noOfDays == 30 || noOfDays == 15) {
//          emailService.sendMail(mailConstants.getMailAdmixureExpiry(),
//              Constants.SUBJECT_ADMIXTURE_EXPIRY,
//              "Please check the Stock. The material is " + processsampleLoad.getProcessSample()
//                  .getIncomingSample().getRawMaterial().getName() + ". Expiry date is "
//                  + processsampleLoad.getExpiryDate());
//        }
//      }
//    });
//  }

  @Scheduled(cron = "0 0 5 * * ?")
  public void notifyTheIncomingSamplePerDay() {
    final LocalDateTime today = LocalDateTime.now();
    java.sql.Date sqlDate = java.sql.Date.valueOf(today.toLocalDate());
    rawMaterialRepository.findAll().forEach(material -> {
      int noOfSamples = incomingSampleRepository
          .findByStatusAndRawMaterialIdAndDate(Status.NEW, material.getId(), sqlDate).size();
      if (noOfSamples > 0) {
        emailService.sendMail(mailConstants.getMailIncomingSamplePerDay(),
            Constants.SUBJECT_INCOMING_SAMPLES_PER_DAY, "Today " + noOfSamples
                + " Samples Arrived for the Raw Material : " + material.getName());
      }
    });
  }

  @Scheduled(cron = "0 0 8 * * ?")
  public void notifyStrengthTestForMixdesign() {
    final LocalDateTime today = LocalDateTime.now();
    mixDesignRepository.findAll().forEach(mixDesign -> {
      long noOfDays =
          ChronoUnit.DAYS.between(mixDesign.getDate().toLocalDate(), today.toLocalDate());
      if (noOfDays == 7 || noOfDays == 28) {
        String mailBody = "Today , " + noOfDays
            + " days over in mixdesign creation. the Mixdesign Code is " + mixDesign.getCode()
            + ", Please test this strength. this mixdesign created on " + mixDesign.getDate()
            + ". target grade of this mixdesign is " + mixDesign.getTargetGrade();
        emailService.sendMail(mailConstants.getMailalertMixDedisgnTest(),
            Constants.SUBJECT_MIX_DESIGN, mailBody);
      }
    });
  }

  @Scheduled(cron = "0 0 8 * * ?")
  public void notifyPendingIncomingSample() {
    final LocalDateTime today = LocalDateTime.now();
    final LocalDateTime incomingDate = today.minusDays(3L);
    System.out.println(incomingDate);
    java.sql.Date sqlDate = java.sql.Date.valueOf(incomingDate.toLocalDate());
    rawMaterialRepository.findAll().forEach(material -> {
      incomingSampleRepository.findByRawMaterialIdAndDate(material.getId(), sqlDate)
          .forEach(incomingSample -> {
            if (incomingSample.getStatus().equals(Status.NEW)) {
              emailService.sendMail(mailConstants.getMailPendingIncomingSample(),
                  Constants.SUBJECT_PENDING_SAMPLES,
                  "This incoming " + material.getName() + " sample didn't start the test from "
                      + incomingSample.getDate() + " .the sample code is "
                      + incomingSample.getCode());
            }
            if (incomingSample.getStatus().equals(Status.PROCESS)) {
              emailService.sendMail(mailConstants.getMailPendingIncomingSample(),
                  Constants.SUBJECT_PENDING_SAMPLES,
                  "This incoming " + material.getName() + " sample didn't complete the test from "
                      + new SimpleDateFormat(incomingSample.getDate().toString())
                      + " .the sample code is " + incomingSample.getCode());
            }
          });
    });
  }
}
