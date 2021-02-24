package com.tokyo.supermix.notification;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import com.tokyo.supermix.data.dto.auth.UserCredentialDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.ConfirmationToken;
import com.tokyo.supermix.data.entities.Customer;
import com.tokyo.supermix.data.entities.EmailGroup;
import com.tokyo.supermix.data.entities.EmailPoints;
import com.tokyo.supermix.data.entities.Employee;
import com.tokyo.supermix.data.entities.FinishProductParameterResult;
import com.tokyo.supermix.data.entities.FinishProductSample;
import com.tokyo.supermix.data.entities.FinishProductTest;
import com.tokyo.supermix.data.entities.IncomingSample;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.MaterialTest;
import com.tokyo.supermix.data.entities.MaterialTestResult;
import com.tokyo.supermix.data.entities.MixDesign;
import com.tokyo.supermix.data.entities.MixDesignConfirmationToken;
import com.tokyo.supermix.data.entities.NotificationDays;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.PlantEquipment;
import com.tokyo.supermix.data.entities.PlantEquipmentCalibration;
import com.tokyo.supermix.data.entities.Project;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.Supplier;
import com.tokyo.supermix.data.entities.SupplierCategory;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.auth.User;
import com.tokyo.supermix.data.enums.AcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.FinishProductSampleType;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.RawMaterialSampleType;
import com.tokyo.supermix.data.enums.Status;
import com.tokyo.supermix.data.enums.UserType;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.CoreTestConfigureRepository;
import com.tokyo.supermix.data.repositories.CustomerRepository;
import com.tokyo.supermix.data.repositories.DesignationRepository;
import com.tokyo.supermix.data.repositories.EmailGroupRepository;
import com.tokyo.supermix.data.repositories.EmailPointsRepository;
import com.tokyo.supermix.data.repositories.EmployeeRepository;
import com.tokyo.supermix.data.repositories.EquipmentRepository;
import com.tokyo.supermix.data.repositories.FinishProductParameterResultRepository;
import com.tokyo.supermix.data.repositories.FinishProductSampleRepository;
import com.tokyo.supermix.data.repositories.FinishProductTestRepository;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.MaterialTestRepository;
import com.tokyo.supermix.data.repositories.MaterialTestResultRepository;
import com.tokyo.supermix.data.repositories.MixDesignRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentCalibrationRepository;
import com.tokyo.supermix.data.repositories.PlantEquipmentRepository;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.ProjectRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.SupplierRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.auth.UserPlantRoleRepository;
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
  @Autowired
  private FinishProductTestRepository finishProductTestRepository;
  @Autowired
  private FinishProductParameterResultRepository finishProductParameterResultRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private CoreTestConfigureRepository coreTestConfigureRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private UserPlantRoleRepository userPlantRoleRepository;
  @Autowired
  private MaterialTestRepository materialTestRepository;

  HttpSession session;

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
    String mailBody = "Today is the " + noOfDays + "th" + " day for the Finish Product Sample "
        + " to conduct the Test.";
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        MailGroupConstance.MIX_DESIGN_EMAIL_GROUP,
        finishProductSample.getMixDesign().getPlant().getCode());
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_MIX_DESIGN, mailBody);
  }

  @Scheduled(cron = "0 0/5 * * *  ? ")
  public void projectalert() {
    projectRepository.findBySentMail(false).forEach(project -> {
      sendProjectEmail(project);
    });
  }

  @Async
  public void sendProjectEmail(Project projectobj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Project project = projectRepository.findByCode(projectobj.getCode());
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
        project.setSentMail(true);
        projectRepository.save(project);
      }
      project.setSentMail(true);
      projectRepository.save(project);
    }
    project.setSentMail(true);
    projectRepository.save(project);
  }

  @Async
  public void sendTestEmail(MaterialTest materialTest) {
    EmailPoints emailPoints = emailPointsRepository
        .findByTestConfigureIdAndSchedule(materialTest.getTestConfigure().getId(), false);
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
            Constants.SUBJECT_MATERIAL_TEST, mailBody);
      }
    }
  }

  @Async
  public void sendFinishProductTestEmail(FinishProductTest finishProductTest) {
    EmailPoints emailPoints = emailPointsRepository
        .findByTestConfigureIdAndSchedule(finishProductTest.getTestConfigure().getId(), false);
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductTest.getFinishProductSample().getMixDesign().getPlant().getCode(),
        emailPoints.getName());
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());

        String mailBody = "<ul><li>Plant-Lab Trial Sample <b>"
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
  public void sendMixdesinApprovelEmail(FinishProductSample finishProductSample,
      MixDesignConfirmationToken mixDesignConfirmationToken, HttpServletRequest request,
      String url) {

    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductSample.getMixDesign().getPlant().getCode(),
        MailGroupConstance.MIX_DESIGN_CONFIRMATION);
    if (emailGroup != null) {
      String body = "<a href=http://" + url + "/api/v1/mix-design/confirmation/"
          + mixDesignConfirmationToken.getConfirmationToken() + ">"
          + "<button style={{background-color:" + "#008CBA" + "}}>Approve</button>" + "</a>";
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        String testMailBody = " ";
        request.getPathInfo();
        for (FinishProductTest finishProductTest : finishProductTestRepository
            .findByFinishProductSampleCode(finishProductSample.getCode())) {
          String result = " ";
          String acceptedValue = " ";

          for (FinishProductParameterResult finishProductParameterResult : finishProductParameterResultRepository
              .findByFinishProductTestCode(finishProductTest.getCode())) {
            result = result + "<li> Result: <b>"
                + finishProductParameterResult.getTestParameter().getParameter().getName() + "- "
                + finishProductParameterResult.getResult() + "</b></li>";
            if (finishProductTest.getTestConfigure().getAcceptedType().equals(AcceptedType.TEST)) {
              AcceptedValue value = acceptedValueRepository.findByTestParameterIdAndTestConfigureId(
                  finishProductParameterResult.getTestParameter().getId(),
                  finishProductTest.getTestConfigure().getId());
              if (value.getConditionRange() == Condition.BETWEEN) {
                acceptedValue = acceptedValue + "maxValue - " + value.getMaxValue().toString()
                    + "minValue - " + value.getMaxValue().toString();
              } else if (value.getConditionRange() == Condition.EQUAL) {
                acceptedValue = acceptedValue + "Equal to - " + value.getValue().toString();

              } else if (value.getConditionRange() == Condition.GREATER_THAN) {
                acceptedValue = acceptedValue + "Greater than - " + value.getValue().toString();

              } else if (value.getConditionRange() == Condition.LESS_THAN) {
                acceptedValue = acceptedValue + "Less than - " + value.getValue().toString();
              }
            } else if (finishProductTest.getTestConfigure().getAcceptedType()
                .equals(AcceptedType.MATERIAL)) {
              MaterialAcceptedValue materialValue = materialAcceptedValueRepository
                  .findByTestConfigureIdAndTestParameterIdAndRawMaterialId(
                      finishProductTest.getTestConfigure().getId(),
                      finishProductParameterResult.getTestParameter().getId(),
                      finishProductParameterResult.getFinishProductTest().getFinishProductSample()
                          .getMixDesign().getRawMaterial().getId());
              if (materialValue.getConditionRange().equals(Condition.BETWEEN)) {
                acceptedValue =
                    acceptedValue + "maxValue - " + materialValue.getMaxValue().toString()
                        + " - minValue - " + materialValue.getMinValue().toString();
              } else if (materialValue.getConditionRange() == Condition.EQUAL) {
                acceptedValue = acceptedValue + "Equal to - " + materialValue.getValue().toString();
              } else if (materialValue.getConditionRange() == Condition.GREATER_THAN) {
                acceptedValue =
                    acceptedValue + " Greater than - : " + materialValue.getValue().toString();
              } else if (materialValue.getConditionRange() == Condition.LESS_THAN) {
                acceptedValue =
                    acceptedValue + "Less than - " + materialValue.getValue().toString();
              }
            } else {
              MaterialAcceptedValue materialValue = materialAcceptedValueRepository
                  .findByTestConfigureIdAndTestParameterIdAndMaterialSubCategoryId(
                      finishProductTest.getTestConfigure().getId(),
                      finishProductParameterResult.getTestParameter().getId(),
                      finishProductParameterResult.getFinishProductTest().getFinishProductSample()
                          .getMixDesign().getRawMaterial().getMaterialSubCategory().getId());
              if (materialValue.getConditionRange().equals(Condition.BETWEEN)) {
                acceptedValue =
                    acceptedValue + "maxValue - " + materialValue.getMaxValue().toString()
                        + " - minValue - " + materialValue.getMinValue().toString();
              } else if (materialValue.getConditionRange() == Condition.EQUAL) {
                acceptedValue = acceptedValue + "Equal to - " + materialValue.getValue().toString();
              } else if (materialValue.getConditionRange() == Condition.GREATER_THAN) {
                acceptedValue =
                    acceptedValue + " Greater than - : " + materialValue.getValue().toString();
              } else if (materialValue.getConditionRange() == Condition.LESS_THAN) {
                acceptedValue =
                    acceptedValue + "Less than - " + materialValue.getValue().toString();
              }
            } ;
          }
          testMailBody = testMailBody + "</br><li> Test: <b>"
              + finishProductTest.getTestConfigure().getTest().getName() + "</b></li>" + result
              + "<li> Acceptance Range:<b>" + acceptedValue + "</b></li>" + "<li>Status:<b>"
              + finishProductTest.getStatus() + "</b></li>" + "<li>Tested Date:<b>"
              + finishProductTest.getDate() + "</b></li></n> </br>";
        } ;
        String message = "<ul><li>Finish Product: <b>"
            + finishProductSample.getMixDesign().getRawMaterial().getName() + "</b></li>"
            + "<li> Mix Design Code: <b>" + finishProductSample.getMixDesign().getCode()
            + "</b></li>" + "<li> Plant-Lab-Trial Sample: <b>" + finishProductSample.getCode()
            + "</b></li>" + "<li> Sample Created Date:<b>" + finishProductSample.getCreatedAt()
            + "</b></li></br>" + "<li> <b>Conducted test Details: </b></li></br>" + "<ul>"
            + testMailBody + "</ul></ul>";
        String mailBody = message + body;
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_MIX_DESIGN_APPROVEL, mailBody);
      }
    }
  }

  @Scheduled(cron = "0 0/5 * * *  ? ")
  public void supplieralert() {
    supplierRepository.findBySentMail(false).forEach(supplier -> {
      sendSupplierEmail(supplier);
    });
  }

  @Async
  public void sendSupplierEmail(Supplier supplierObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Supplier supplier = supplierRepository.findById(supplierObj.getId()).get();
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
        supplier.setSentMail(true);
        supplierRepository.save(supplier);
      }
      supplier.setSentMail(true);
      supplierRepository.save(supplier);
    }
    supplier.setSentMail(true);
    supplierRepository.save(supplier);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void employeealert() {
    employeeRepository.findBySentMail(false).forEach(employee -> {
      sendEmployeeEmail(employee);
    });
  }

  @Async
  public void sendEmployeeEmail(Employee employeeObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Employee employee = employeeRepository.findById(employeeObj.getId()).get();
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
          employee.setSentMail(true);
          employeeRepository.save(employee);
        }
        employee.setSentMail(true);
        employeeRepository.save(employee);
      }
      employee.setSentMail(true);
      employeeRepository.save(employee);
    } else {
      if (employee.getEmail() != null) {
        String mailBody =
            "Dear " + employee.getFirstName() + " Welcome to our company as " + designationName;
        emailService.sendMail(employee.getEmail(), Constants.SUBJECT_NEW_EMPLOYEE, mailBody);
        employee.setSentMail(true);
        employeeRepository.save(employee);
      }

    }
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void IncomingSamplealert() {
    incomingSampleRepository
        .findBySentMailAndRawMaterialSampleType(false, RawMaterialSampleType.INCOMING_SAMPLE)
        .forEach(incoming -> {
          sendIncomingSampleEmail(incoming);
        });
  }

  @Async
  public void sendIncomingSampleEmail(IncomingSample incomingSampleObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    IncomingSample incomingSample =
        incomingSampleRepository.findById(incomingSampleObj.getCode()).get();
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
        incomingSample.setSentMail(true);
        incomingSampleRepository.save(incomingSample);
      }
      incomingSample.setSentMail(true);
      incomingSampleRepository.save(incomingSample);
    }
    incomingSample.setSentMail(true);
    incomingSampleRepository.save(incomingSample);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void finishProductSamplealert() {
    finishProductSampleRepository
        .findBySentMailAndFinishProductSampleType(false, FinishProductSampleType.LAB_TRIAL_SAMPLE)
        .forEach(finishProduct -> {
          sendFinishProductSampleEmail(finishProduct);
        });
  }

  @Async
  public void sendFinishProductSampleEmail(FinishProductSample finishProductSampleObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findByCode(finishProductSampleObj.getCode());
    MixDesign mixDesign =
        mixDesignRepository.findByCode(finishProductSample.getMixDesign().getCode());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        mixDesign.getPlant().getCode(), MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = "Plant-Lab Trial Sample created for mix design - " + mixDesign.getCode();
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE, mailBody);
        finishProductSample.setSentMail(true);
        finishProductSampleRepository.save(finishProductSample);
      }
      finishProductSample.setSentMail(true);
      finishProductSampleRepository.save(finishProductSample);
    }
    finishProductSample.setSentMail(true);
    finishProductSampleRepository.save(finishProductSample);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void DeliverySamplealert() {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    finishProductSampleRepository
        .findBySentMailAndFinishProductSampleType(false, FinishProductSampleType.DELIVERY_SAMPLE)
        .forEach(finishProduct -> {
          sendFinishProductSampleIssueEmail(finishProduct);
        });
  }

  @Async
  public void sendFinishProductSampleIssueEmail(FinishProductSample finishProductSampleObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    FinishProductSample finishProductSample =
        finishProductSampleRepository.findByCode(finishProductSampleObj.getCode());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        finishProductSample.getMixDesign().getPlant().getCode(),
        MailGroupConstance.CREATE_FINISH_PRODUCT_SAMPLE_ISSUE_);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = "";
        String RawMaterialName = finishProductSample.getMixDesign().getRawMaterial().getName();
        if (finishProductSample.getProject() != null) {
          mailBody = "Material name " + RawMaterialName + " is deliveried for the customer, "
              + finishProductSample.getProject().getCustomer().getName();
        } else {
          mailBody = "Material name " + RawMaterialName + " is delivered";
        }
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                emailGroup.getEmailPoints().getName(), emailGroup.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_FINISH_PRODUCT_SAMPLE_ISSUE, mailBody);
        finishProductSample.setSentMail(true);
        finishProductSampleRepository.save(finishProductSample);
      }
      finishProductSample.setSentMail(true);
      finishProductSampleRepository.save(finishProductSample);
    }
    finishProductSample.setSentMail(true);
    finishProductSampleRepository.save(finishProductSample);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void plantEquipmentalert() {
    plantEquipmentRepository.findBySentMail(false).forEach(plantequipment -> {
      sendPlantEquipmentEmail(plantequipment);
    });
  }

  @Async
  public void sendPlantEquipmentEmail(PlantEquipment plantequipmentObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PlantEquipment plantequipment =
        plantEquipmentRepository.findById(plantequipmentObj.getSerialNo()).get();
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
        plantequipment.setSentMail(true);
        plantEquipmentRepository.save(plantequipment);
      }
      plantequipment.setSentMail(true);
      plantEquipmentRepository.save(plantequipment);
    }
    plantequipment.setSentMail(true);
    plantEquipmentRepository.save(plantequipment);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void rawmaterialalert() {
    rawMaterialRepository.findBySentMail(false).forEach(rawmaterial -> {
      sendRawmaterialCreationEmail(rawmaterial);
    });
  }

  @Async
  public void sendRawmaterialCreationEmail(RawMaterial rawMaterialob) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialob.getId()).get();
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_RAW_MATERIAL);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        MaterialSubCategory materialSubCategory = materialSubCategoryRepository
            .findById(rawMaterial.getMaterialSubCategory().getId()).get();
        String materialSubCategoryName = materialSubCategoryRepository
            .findById(rawMaterial.getMaterialSubCategory().getId()).get().getName();
        String materialCategoryName = materialSubCategory.getMaterialCategory().getName();
        String mailBody =
            "Material " + rawMaterial.getName() + " is newly added under  Main category - "
                + materialCategoryName + " and  Sub category - " + materialSubCategoryName;;
        List<String> reciepientList = emailRecipientService
            .getEmailsByEmailNotification(MailGroupConstance.CREATE_RAW_MATERIAL);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_RAW_MATERIAL, mailBody);
        rawMaterial.setSentMail(true);
        rawMaterialRepository.save(rawMaterial);
      }
      rawMaterial.setSentMail(true);
      rawMaterialRepository.save(rawMaterial);
    }
    rawMaterial.setSentMail(true);
    rawMaterialRepository.save(rawMaterial);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void customeralert() {
    customerRepository.findBySentMail(false).forEach(customer -> {
      sendCustomerCreationEmail(customer);
    });
  }

  @Async
  public void sendCustomerCreationEmail(Customer customerObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Customer customer = customerRepository.findById(customerObj.getId()).get();
    customer.getPlant().forEach(plant -> {
      EmailGroup emailGroup = emailGroupRepository
          .findByPlantCodeAndEmailPointsName(plant.getCode(), MailGroupConstance.CREATE_CUSTOMER);
      if (emailGroup != null) {
        if (emailGroup.isStatus()) {
          String mailBody =
              "Customer " + customer.getName() + " newly added from " + customer.getAddress() + ".";
          List<String> reciepientList = emailRecipientService
              .getEmailsByEmailNotification(MailGroupConstance.CREATE_CUSTOMER);
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_CUSTOMER, mailBody);
          customer.setSentMail(true);
          customerRepository.save(customer);
        }
        customer.setSentMail(true);
        customerRepository.save(customer);
      }
      customer.setSentMail(true);
      customerRepository.save(customer);
    });
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void plantalert() {
    plantRepository.findBySentMail(false).forEach(plant -> {
      sendPlantCreationEmail(plant);
    });
  }

  @Async
  public void sendPlantCreationEmail(Plant plantObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Plant plant = plantRepository.findById(plantObj.getCode()).get();
    String mailBody = plant.getName() + " Plant is created at " + plant.getAddress();
    EmailGroup emailGroup =
        emailGroupRepository.findByEmailPointsName(MailGroupConstance.CREATE_PLANT);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotification(MailGroupConstance.CREATE_PLANT);
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PLANT, mailBody);
        plant.setSentMail(true);
        plantRepository.save(plant);
      }
      plant.setSentMail(true);
      plantRepository.save(plant);
    }
    plant.setSentMail(true);
    plantRepository.save(plant);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void sendProcessSample() {
    incomingSampleRepository
        .findBySentMailAndRawMaterialSampleType(false, RawMaterialSampleType.PROCESS_SAMPLE)
        .forEach(incoming -> {
          sendProcessSampleCreationEmail(incoming);
        });
  }

  @Async
  public void sendProcessSampleCreationEmail(IncomingSample incomingSampleObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    IncomingSample incomingSample =
        incomingSampleRepository.findById(incomingSampleObj.getCode()).get();
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        incomingSample.getPlant().getCode(), MailGroupConstance.CREATE_PROCESS_SAMPLE);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String suplierName =
            supplierRepository.findById(incomingSample.getSupplier().getId()).get().getName();
        String rawMaterialName =
            rawMaterialRepository.findById(incomingSample.getRawMaterial().getId()).get().getName();
        String mailBody = "New Process sample is created for  " + rawMaterialName
            + " from the Supplier " + suplierName + ".";
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_PROCESS_SAMPLE, incomingSample.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_PROCESS_SAMPLE, mailBody);
        incomingSample.setSentMail(true);
        incomingSampleRepository.save(incomingSample);
      }
      incomingSample.setSentMail(true);
      incomingSampleRepository.save(incomingSample);
    }
    incomingSample.setSentMail(true);
    incomingSampleRepository.save(incomingSample);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void mixdesignalert() {
    mixDesignRepository.findBySentMail(false).forEach(mixdesign -> {
      sendMixDesignCreationEmail(mixdesign);
    });
  }

  @Async
  public void sendMixDesignCreationEmail(MixDesign mixDesignObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    MixDesign mixDesign = mixDesignRepository.findByCode(mixDesignObj.getCode());
    EmailGroup emailGroup = emailGroupRepository.findByPlantCodeAndEmailPointsName(
        mixDesign.getPlant().getCode(), MailGroupConstance.CREATE_MIX_DESIGN);
    if (emailGroup != null) {
      if (emailGroup.isStatus()) {
        String mailBody = "New Mix Design - " + mixDesign.getCode()
            + " is created for the Finish Good " + mixDesign.getRawMaterial().getName();
        List<String> reciepientList =
            emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                MailGroupConstance.CREATE_MIX_DESIGN, mixDesign.getPlant().getCode());
        emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
            Constants.SUBJECT_MIX_DESIGN, mailBody);
        mixDesign.setSentMail(true);
        mixDesignRepository.save(mixDesign);
      }
      mixDesign.setSentMail(true);
      mixDesignRepository.save(mixDesign);
    }
    mixDesign.setSentMail(true);
    mixDesignRepository.save(mixDesign);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void calibrationalert() {
    plantEquipmentCalibrationRepository.findBySentMail(false).forEach(calibration -> {
      sendcalibrationCreationEmail(calibration);
    });
  }

  @Async
  public void sendcalibrationCreationEmail(PlantEquipmentCalibration plantEquipmentCalibrationObj) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    PlantEquipmentCalibration plantEquipmentCalibration =
        plantEquipmentCalibrationRepository.findById(plantEquipmentCalibrationObj.getId()).get();
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
          String mailBody =
              equipmentName + " is Calibrated by " + plantEquipmentCalibration.getCalibrationType()
                  + " by the supplier :-" + supplierName + " and the "
                  + plantEquipmentCalibration.getDueDate() + " at " + plantName;
          List<String> reciepientList =
              emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                  MailGroupConstance.CREATE_PLANT_EQUIPMENT_CALIBRATION, plantCode);
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_CALIBRATED, mailBody);
          plantEquipmentCalibration.setSentMail(true);
          plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
        } else {
          String userName = userRepository.findById(plantEquipmentCalibration.getUser().getId())
              .get().getUserName();
          String mailBody = equipmentName + " is Calibrated by "
              + plantEquipmentCalibration.getCalibrationType() + " by the user :-" + userName
              + " and the " + plantEquipmentCalibration.getDueDate() + " at " + plantName;
          List<String> reciepientList =
              emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
                  MailGroupConstance.CREATE_PLANT_EQUIPMENT_CALIBRATION, plantCode);
          emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
              Constants.SUBJECT_CALIBRATED, mailBody);
          plantEquipmentCalibration.setSentMail(true);
          plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
        }

      }
      plantEquipmentCalibration.setSentMail(true);
      plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
    }
    plantEquipmentCalibration.setSentMail(true);
    plantEquipmentCalibrationRepository.save(plantEquipmentCalibration);
  }

  @Scheduled(cron = "0 0/5 * * * ? ")
  public void useralert() {
    userRepository.findBySentMailAndUserType(false, UserType.PLANT_USER).forEach(user -> {
      List<Long> roleIds = userPlantRoleRepository.findByUserId(user.getId()).stream()
          .map(role -> role.getId()).collect(Collectors.toList());
      sendPlantUserCreationEmail(user, roleIds);
    });
  }

  @Async
  public void sendPlantUserCreationEmail(User userObj, List<Long> roles) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    User user = userRepository.findUserById(userObj.getId());
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
        user.setSentMail(true);
        userRepository.save(user);
      }
      user.setSentMail(true);
      userRepository.save(user);
    }
    user.setSentMail(true);
    userRepository.save(user);
  }

  @Async
  public void sendNonPlantUserCreationEmail(User user, List<Long> roles) {
    Integer seconds = 5;
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
            Constants.SUBJECT_USERS_CREATION, mailBody);
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

  @Scheduled(cron = "0 35 23 * * * ")
  public void reminderForFinishProductSampleTest() {
    List<Status> statusli = new ArrayList<Status>();
    statusli.add(Status.NEW);
    statusli.add(Status.PROCESS);
    final LocalDateTime today = LocalDateTime.now();
    for (TestConfigure testconfigure : testConfigureRepository
        .findByTestTypeAndDueDayNotNull(MainType.FINISH_PRODUCT)) {
      List<RawMaterial> rawMaterialList =
          coreTestConfigureRepository.findBytestConfigureId(testconfigure.getId()).stream()
              .map(coretest -> coretest.getRawMaterial()).collect(Collectors.toList());
      for (RawMaterial rawMaterial : rawMaterialList) {
        List<FinishProductSample> finishProductSamples =
            finishProductSampleRepository.findByMixDesignRawMaterialId(rawMaterial.getId());
        for (FinishProductSample finishProductSample : finishProductSamples) {
          if (finishProductSample.getStatus().equals(Status.NEW)
              || !(finishProductTestRepository.existsByFinishProductSampleCodeAndTestConfigureId(
                  finishProductSample.getCode(), testconfigure.getId()))) {
            long noOfDays = ChronoUnit.DAYS.between(
                finishProductSample.getCreatedAt().toLocalDateTime().toLocalDate(),
                today.toLocalDate());
            EmailPoints emailPoints =
                emailPointsRepository.findByTestConfigureIdAndSchedule(testconfigure.getId(), true);
            if (emailPoints != null) {
              String plantCode = finishProductSample.getMixDesign().getPlant().getCode();
              EmailGroup emailGroup = emailGroupRepository
                  .findByPlantCodeAndEmailPointsName(plantCode, emailPoints.getName());
              if (emailGroup != null) {
                List<NotificationDays> notificationDaysList =
                    emailNotificationDaysService.getByEmailGroup(emailPoints.getName(), plantCode);
                if (notificationDaysList != null) {
                  notificationDaysList.forEach(notificationday -> {
                    if (noOfDays == notificationday.getDays()) {
                      sendfinishProductSampleEmail(testconfigure, noOfDays, finishProductSample);
                    }
                  });
                }
              }
            }
          }
        }
      }
    }
  }

  private void sendfinishProductSampleEmail(TestConfigure testconfigure, long noOfDays,
      FinishProductSample finishProductSample) {
    EmailPoints emailPoints =
        emailPointsRepository.findByTestConfigureIdAndSchedule(testconfigure.getId(), true);
    String mailBody = "Today is the " + noOfDays + "th" + " day for the Plant-Lab Trial Sample "
        + finishProductSample.getCode() + " to conduct the Test.";
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        emailPoints.getName(), finishProductSample.getMixDesign().getPlant().getCode());
    reciepientList.add(finishProductSample.getUser().getEmail());
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_FINISH_PRODUCT_SAMPLE_REMINDER, mailBody);
  }

  @Scheduled(cron = "0 28 23 * * * ")
  public void reminderForMaterialTest() {
    final LocalDateTime today = LocalDateTime.now();
    List<Status> statusli = new ArrayList<Status>();
    statusli.add(Status.NEW);
    statusli.add(Status.PROCESS);
    for (TestConfigure testconfigure : testConfigureRepository
        .findByTestTypeAndDueDayNotNull(MainType.RAW_MATERIAL)) {
      List<RawMaterial> rawMaterialList =
          coreTestConfigureRepository.findBytestConfigureId(testconfigure.getId()).stream()
              .map(coretest -> coretest.getRawMaterial()).collect(Collectors.toList());
      for (RawMaterial rawMaterial : rawMaterialList) {
        List<IncomingSample> incomingSamples =
            incomingSampleRepository.findByRawMaterialId(rawMaterial.getId());
        for (IncomingSample incomingSample : incomingSamples) {
          if (incomingSample.getStatus().equals(Status.NEW)
              || !(materialTestRepository.existsByIncomingSampleCodeAndTestConfigureId(
                  incomingSample.getCode(), testconfigure.getId()))) {
            long noOfDays = ChronoUnit.DAYS.between(
                incomingSample.getCreatedAt().toLocalDateTime().toLocalDate(), today.toLocalDate());
            EmailPoints emailPoints =
                emailPointsRepository.findByTestConfigureIdAndSchedule(testconfigure.getId(), true);
            if (emailPoints != null) {
              String plantCode = incomingSample.getPlant().getCode();
              EmailGroup emailGroup = emailGroupRepository
                  .findByPlantCodeAndEmailPointsName(plantCode, emailPoints.getName());
              if (emailGroup != null) {
                List<NotificationDays> notificationDaysList =
                    emailNotificationDaysService.getByEmailGroup(emailPoints.getName(), plantCode);
                if (notificationDaysList != null) {
                  notificationDaysList.forEach(notificationday -> {
                    if (noOfDays == notificationday.getDays()) {
                      sendMixDesignTestEmail(testconfigure, noOfDays, incomingSample);
                    }
                  });
                }
              }
            }
          }
        }
      }
    }
  }

  private void sendMixDesignTestEmail(TestConfigure testConfigure, long noOfDays,
      IncomingSample incomingSample) {
    EmailPoints emailPoints =
        emailPointsRepository.findByTestConfigureIdAndSchedule(testConfigure.getId(), true);
    String mailBody = "Today is the " + noOfDays + "th" + " day for the Incoming sample "
        + incomingSample.getCode() + " to conduct the Test.";
    List<String> reciepientList = emailRecipientService.getEmailsByEmailNotificationAndPlantCode(
        emailPoints.getName(), incomingSample.getPlant().getCode());
    reciepientList.add(incomingSample.getUser().getEmail());
    emailService.sendMailWithFormat(reciepientList.toArray(new String[reciepientList.size()]),
        Constants.SUBJECT_MATERIAL_TEST_REMINDER, mailBody);
  }
}
