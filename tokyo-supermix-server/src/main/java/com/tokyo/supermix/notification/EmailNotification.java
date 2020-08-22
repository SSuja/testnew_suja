package com.tokyo.supermix.notification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.entities.ConfirmationToken;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.entities.ProcessSample;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.DesignationRepository;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRoleRepository;
import com.tokyo.supermix.server.services.EmailNotificationDaysService;
import com.tokyo.supermix.server.services.EmailRecipientService;
import com.tokyo.supermix.server.services.EmailService;
import com.tokyo.supermix.util.Constants;
import com.tokyo.supermix.util.MailGroupConstance;
import com.tokyo.supermix.util.privilege.PrivilegeConstants;

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
  @Autowired
  private SupplierRepository supplierRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private DesignationRepository designationRepository;
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MixDesignRepository mixDesignRepository;
  @Autowired
  private EquipmentRepository equipmentRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private MaterialTestResultRepository materialTestResultRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PlantEquipmentRepository plantEquipmentRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PlantRoleRepository plantRoleRepository;

  @Scheduled(cron = "${mail.notificationTime.plantEquipment}")
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
          sendEquipmentMail(calibration, noOfDays);
        }
      });
    });
  }

  private void sendEquipmentMail(PlantEquipmentCalibration calibration, long noOfDays) {
    List<String> equipmentCalibrationEmailList =
        emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
            MailGroupConstance.PLANT_EQUIPMENT_CALIBRATION_GROUP,
            calibration.getPlantEquipment().getPlant().getCode());
    emailService.sendMail(
        equipmentCalibrationEmailList.toArray(new String[equipmentCalibrationEmailList.size()]),
        Constants.SUBJECT_EQUIPMENT_CALIBRATION,
        "Calibration Due date for the " + calibration.getPlantEquipment().getEquipment().getName()
            + " remaining day - " + noOfDays);
  }

  @Scheduled(cron = "${mail.notificationTime.strengthTestMixDesign}")
  public void notifyStrengthTestForMixdesign() {
    final LocalDateTime today = LocalDateTime.now();
    finishProductSampleRepository.findAll().forEach(finishProductSample -> {
      long noOfDays =
          ChronoUnit.DAYS.between(finishProductSample.getDate().toLocalDate(), today.toLocalDate());
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
    String mailBody = "Today is the " + noOfDays + "th" + " day for the Finish Product Sample - "
        + finishProductSample.getWorkOrderNo() + " to conduct the Test.";
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        MailGroupConstance.MIX_DESIGN_EMAIL_GROUP,
        finishProductSample.getMixDesign().getPlant().getCode());
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_MIX_DESIGN, mailBody);
  }

  @Async
  public void sendProjectEmail(Project project) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        project.getPlant().getCode(), MailGroupConstance.CREATE_PROJECT);
    String customerName =
        customerRepository.findById(project.getCustomer().getId()).get().getName();
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = project.getName() + " Project is newly added for " + customerName + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_PROJECT, mailBody);
      }
    }
  }

  @Async
  public void sendTestEmail(MaterialTest materialTest) {
    EmailPoints emailPoints = emailPointsRepository.findByMaterialSubCategoryIdAndTestId(
        materialTest.getTestConfigure().getMaterialSubCategory().getId(),
        materialTest.getTestConfigure().getTest().getId());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        materialTest.getIncomingSample().getPlant().getCode(), emailPoints.getName());
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<MaterialTestResult> materialTestAverageList =
            materialTestResultRepository.findByMaterialTestCode(materialTest.getCode());
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        String average = "";
        for (MaterialTestResult materialTestResult : materialTestAverageList) {
          average = average + materialTestResult.getResult() + ",";
        }
        String mailBody = "<ul><li>Incoming Sample <b>" + materialTest.getIncomingSample().getCode()
            + "</b></li><li> Test Name <b>" + materialTest.getTestConfigure().getTest().getName()
            + "</b></li><li> Material <b>"
            + materialTest.getIncomingSample().getRawMaterial().getName()
            + "</b></li><li> Supplier <b>"
            + materialTest.getIncomingSample().getSupplier().getName() + "</b></li><li> Results <b>"
            + average + "</b></li><li> Status <b>" + materialTest.getStatus() + "</b></li></ul>";

        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_MATRIAL_TEST, mailBody);
      }
    }
  }

  @Async
  public void sendFinishProductTestEmail(FinishProductTest finishProductTest) {
    EmailPoints emailPoints = emailPointsRepository.findByMaterialSubCategoryIdAndTestId(
        finishProductTest.getTestConfigure().getMaterialSubCategory().getId(),
        finishProductTest.getTestConfigure().getTest().getId());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductTest.getFinishProductSample().getMixDesign().getPlant().getCode(),
        emailPoints.getName());
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        String mailBody = "<ul><li>Finish Product Sample <b>"
            + finishProductTest.getFinishProductSample().getCode() + "</b></li><li> Test Name <b>"
            + finishProductTest.getTestConfigure().getTest().getName()
            + "</b></li><li> Material <b>"
            + finishProductTest.getTestConfigure().getMaterialCategory().getName()
            + "</li><li> Status <b>" + finishProductTest.getStatus() + "</b></li></ul>";
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_CONCRETE_TEST, mailBody);
      }
    }
  }

  @Async
  public void sendSupplierEmail(Supplier supplier) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        supplier.getPlant().getCode(), MailGroupConstance.CREATE_SUPPLIER);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<SupplierCategory> supplierCategoriesList = supplier.getSupplierCategories();
        String categories = "";
        for (SupplierCategory supplierCategory : supplierCategoriesList) {
          categories = categories + supplierCategory.getCategory() + ",";
        }
        String supplierCategories = categories.replaceAll(",$", ".");
        String mailBody =
            "Supplier " + supplier.getName() + " newly created under the " + supplierCategories;
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_SUPPLIER, mailBody);
      }
    }
  }

  @Async
  public void sendEmployeeEmail(Employee employee) {
    String designationName =
        designationRepository.findById(employee.getDesignation().getId()).get().getName();
    if (employee.getPlant() != null) {
      String plantName = plantRepository.findById(employee.getPlant().getCode()).get().getName();
      if (employee.getEmail() != null) {
        String mailBody = " Welcome to " + plantName + " as " + designationName;
        emailService.sendMail(employee.getEmail(), Constants.SUBJECT_NEW_EMPLOYEE, mailBody);
      }
      EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
          employee.getPlant().getCode(), MailGroupConstance.CREATE_EMPLOYEE);
      if (emailGroup != null) {
        if (emailGroup.isStatus()) {
          String mailBody = "Employee " + employee.getFirstName() + " " + employee.getLastName()
              + " entrolled " + "as " + designationName + ".";
          List<String> reciepientList =
              emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                  emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_NEW_EMPLOYEE, mailBody);
        }
      }
    } else {
      if (employee.getEmail() != null) {
        String mailBody =
            "Dear " + employee.getFirstName() + " Welcome to our company as " + designationName;
        emailService.sendMail(employee.getEmail(), Constants.SUBJECT_NEW_EMPLOYEE, mailBody);
      }

    }
  }

  @Async
  public void sendIncomingSampleEmail(IncomingSample incomingSample) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        incomingSample.getPlant().getCode(), MailGroupConstance.CREATE_INCOMING_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String suplierName =
            supplierRepository.findById(incomingSample.getSupplier().getId()).get().getName();
        String rawMaterialName =
            rawMaterialRepository.findById(incomingSample.getRawMaterial().getId()).get().getName();
        String mailBody = "New Incoming sample is created for  " + rawMaterialName
            + " from the Supplier " + suplierName + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_NEW_INCOMING_SAMPLE, mailBody);
      }
    }
  }

  @Async
  public void sendFinishProductSampleEmail(FinishProductSample finishProductSample) {
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        mixDesign.getPlant().getCode(), MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = "Finish Product sample created for mix design - " + mixDesign.getCode()
            + " to the grade " + mixDesign.getTargetGrade() + " and Slump"
            + mixDesign.getTargetSlump();
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE, mailBody);
      }
    }
  }

  @Async
  public void sendFinishProductSampleIssueEmail(FinishProductSampleIssue finishProductSampleIssue) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductSampleIssue.getFinishProductSample().getMixDesign().getPlant().getCode(),
        MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE_ISSUE_);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String customerName = customerRepository
            .findById(finishProductSampleIssue.getProject().getCustomer().getId()).get().getName();
        String mailBody = "Finish product deliveried for Finish Product sample of "
            + finishProductSampleIssue.getCode() + " by " + customerName;
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE_ISSUE, mailBody);
      }
    }
  }

  @Async
  public void sendPlantEquipmentCalibrationEmail(PlantEquipment plantequipment) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        plantequipment.getPlant().getCode(), MailGroupConstance.CREATE_PLANT_EQUIPMENT);
    String plantName =
        plantRepository.findPlantByCode(plantequipment.getPlant().getCode()).getName();
    String equipmentName =
        equipmentRepository.findById(plantequipment.getEquipment().getId()).get().getName();
    String mailBody = " Equipment " + equipmentName + " is newly added for " + plantName + ".";
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

  @Async
  public void sendRawmaterialCreationEmail(RawMaterial rawMaterial) {
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_RAW_MATERIAL);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String materialSubCategoryName = materialSubCategoryRepository
            .findById(rawMaterial.getMaterialSubCategory().getId()).get().getName();
        String mailBody = "Material " + rawMaterial.getName() + " is newly added under "
            + materialSubCategoryName;
        List<String> reciepientList = emailRecipientService
            .getEmailsByEmailNotification(MailGroupConstance.CREATE_RAW_MATERIAL);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_RAW_MATERIAL, mailBody);
      }
    }
  }

  @Async
  public void sendCustomerCreationEmail(Customer customer) {
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_CUSTOMER);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody =
            "Customer " + customer.getName() + " newly added from " + customer.getAddress() + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotification(MailGroupConstance.CREATE_CUSTOMER);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_CUSTOMER, mailBody);
      }
    }
  }

  @Async
  public void sendPlantCreationEmail(Plant plant) {
    String mailBody = plant.getName() + " Plant is created at " + plant.getAddress();
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_PLANT);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotification(MailGroupConstance.CREATE_PLANT);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PLANT, mailBody);
      }
    }
  }

  @Async
  public void sendProcessSampleCreationEmail(ProcessSample processSample) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        processSample.getIncomingSample().getPlant().getCode(),
        MailGroupConstance.CREATE_PROCESS_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String rawMaterialName =
            rawMaterialRepository.findById(processSample.getRawMaterial().getId()).get().getName();
        String supplierName = incomingSampleRepository
            .findById(processSample.getIncomingSample().getCode()).get().getSupplier().getName();
        String IncomingSample = incomingSampleRepository
            .findById(processSample.getIncomingSample().getCode()).get().getCode();
        String mailBody = "Material Load  is arrived from " + supplierName
            + " for the Passed Incoming sample - " + IncomingSample + ", " + rawMaterialName;
        List<String> reciepientList = emailRecipientService
            .getEmailsByEmailNotificationAndPlantCode(MailGroupConstance.CREATE_PROCESS_SAMPLE,
                processSample.getIncomingSample().getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PROCESS_SAMPLE, mailBody);
      }
    }
  }

  @Async
  public void sendMixDesignCreationEmail(MixDesign mixDesign) {
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        mixDesign.getPlant().getCode(), MailGroupConstance.CREATE_MIX_DESIGN);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = "New Mix Design - " + mixDesign.getCode() + " added to the Grade "
            + mixDesign.getTargetGrade() + " and Slump " + mixDesign.getTargetSlump() + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_MIX_DESIGN, mixDesign.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_MIX_DESIGN, mailBody);
      }
    }
  }

  @Async
  public void sendcalibrationCreationEmail(PlantEquipmentCalibration plantEquipmentCalibration) {
    String plantCode = plantEquipmentRepository
        .findById(plantEquipmentCalibration.getPlantEquipment().getSerialNo()).get().getPlant()
        .getCode();
    String plantName = plantRepository.findPlantByCode(plantCode).getName();
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(plantCode,
        MailGroupConstance.CREATE_PLANT_EQUIPMENT_CALIBRATION);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String equipmentName = plantEquipmentRepository
            .findById(plantEquipmentCalibration.getPlantEquipment().getSerialNo()).get()
            .getEquipment().getName();
        if (plantEquipmentCalibration.getSupplier() != null) {
          String supplierName = supplierRepository
              .findById(plantEquipmentCalibration.getSupplier().getId()).get().getName();
          String mailBody = equipmentName + " is Calibrated by "
              + plantEquipmentCalibration.getCalibrationType() + " by the supplier," + supplierName
              + " and the " + plantEquipmentCalibration.getDueDate() + " at " + plantName;
          List<String> reciepientList =
              emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                  MailGroupConstance.CREATE_PLANT_EQUIPMENT_CALIBRATION, plantCode);
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_CALIBRATED, mailBody);
        } else {
          String userName = userRepository.findById(plantEquipmentCalibration.getUser().getId())
              .get().getUserName();
          String mailBody = equipmentName + " is Calibrated by "
              + plantEquipmentCalibration.getCalibrationType() + " by the user," + userName
              + " and the " + plantEquipmentCalibration.getDueDate() + " at " + plantName;
          List<String> reciepientList =
              emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                  MailGroupConstance.CREATE_PLANT_EQUIPMENT_CALIBRATION, plantCode);
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_CALIBRATED, mailBody);
        }

      }
    }
  }

  @Async
  public void sendPlantUserCreationEmail(User user, List<Long> roles) {
    Plant plant = employeeRepository.findById(user.getEmployee().getId()).get().getPlant();
    Employee employee = employeeRepository.findById(user.getEmployee().getId()).get();
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(plant.getCode(),
        MailGroupConstance.CREATE_PLANT_USER);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String plantRoleNames = " ";
        for (Long roleId : roles) {
          if (user.getUserType().equals(UserType.PLANT_USER)) {
            String plantRoleName = plantRoleRepository.findById(roleId).get().getName();
            plantRoleNames = plantRoleNames + plantRoleName;
          }
        }
        String mailBody = plant.getName() + "'s " + employee.getDesignation().getName() + " "
            + employee.getFirstName() + " " + employee.getLastName()
            + " is assigned to the role of " + plantRoleNames + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_PLANT_USER, user.getEmployee().getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_USER_CREATION, mailBody);
      }
    }
  }

  @Async
  public void sendNonPlantUserCreationEmail(User user, List<Long> roles) {
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_NON_PLANT_USER);
    Employee employee = employeeRepository.findById(user.getEmployee().getId()).get();
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String plantRoleNames = " ";
        for (Long roleId : roles) {
          if (user.getUserType().equals(UserType.NON_PLANT_USER)) {
            String plantRoleName = roleRepository.findById(roleId).get().getName();
            plantRoleNames = plantRoleNames + plantRoleName;
          }
        }
        String mailBody = employee.getFirstName() + " " + employee.getLastName()
            + " is assigned to the role of " + plantRoleNames + ".";
        List<String> reciepientList = emailRecipientService
            .getEmailsByEmailNotification(MailGroupConstance.CREATE_NON_PLANT_USER);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_USER_CREATION, mailBody);
      }
    }
  }

  @Async
  public void sendUserCreationEmail(UserCredentialDto userDto) {
    if (userDto != null) {
      String message = "Your Account sucessfully created. Your Username is " + userDto.getUserName()
          + ". Password is " + userDto.getPassword();
      emailService.sendMail(userDto.getEmail(), Constants.SUBJECT_NEW_USER, message);
    }
  }

  @Async
  public void sendEmployeeConformation(Employee employee, ConfirmationToken confirmationToken,
      HttpServletRequest request) {
    String[] empStrings = {employee.getEmail()};
    String message = "To confirm your account " + "<a href=http://" + request.getServerName() + ":"
        + request.getServerPort() + request.getContextPath() + "/api/v1/employee/confirmation/"
        + confirmationToken.getConfirmationToken() + ">" + "<button style={{background-color:"
        + "#008CBA" + "}}>Click here</button>" + "</a>";
    emailService.sendMailWithFormat(empStrings, Constants.SUBJECT_EMPLOYEE_CREATION, message);
  }

  @Async
  public void sendForgotConformation(User user, String token) {
    emailService.sendMail(user.getEmail(), Constants.SUBJECT_FORGOT_PASSWORD,
        PrivilegeConstants.MESSAGE_OF_FORGOT_PASSWORD + token);

  }
}
