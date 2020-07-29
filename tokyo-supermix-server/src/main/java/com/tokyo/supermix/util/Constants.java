package com.tokyo.supermix.util;

public class Constants {
  // for email notification
  public static final String ADMIXTURE = "Admixture";
  public static final String SUBJECT_EQUIPMENT_CALIBRATION = "Notification : Calibration Reminder";
  public static final String SUBJECT_ADMIXTURE_EXPIRY = "Notification : expiry date for Admixture";
  public static final String SUBJECT_INCOMING_SAMPLES_PER_DAY =
      "Notification : Incoming Sample Reminder";
  public static final String SUBJECT_MIX_DESIGN = "Notification : About Mixdesign ";
  public static final String SUBJECT_CALIBRATED = "Notification :  Equipment is Calibrated ";
  public static final String SUBJECT_PENDING_SAMPLES = "Notification : Pending Incoming Sample";
  public static final String SUBJECT_NEW_USER = "Notification : Congratulations!";
  public static final String SUBJECT_FORGOT_PASSWORD = "Forgot Password";
  public static final String SUBJECT_OF_SUMMARY_REPORT =
      "Summary Details Report Of Incoming Sample";
  public static final String SUBJECT_OF_DELIVERY_REPORT =
      "Delivery Details Report Of Incoming Sample";
  public static final String BODY_FOR_REPORT = "Here you can find the Test Details Report";
  public static final String DELIVERY_REPORT = "delivery-report.pdf";
  public static final String SUMMARY_REPORT = "summary-report.pdf";
  public static final String DELIVERY_REPORT_MOISTURE_TEST = "Moisture Test";
  public static final String SUBJECT_NEW_PROJECT = "Notification : About Project Creation ";
  public static final String SUBJECT_NEW_SUPPLIER = "Notification : About Supplier Creation ";
  public static final String SUBJECT_MATRIAL_TEST = "Notification : About Matrial test result ";
  public static final String SUBJECT_CONCRETE_TEST = "Notification : About Concrete test result ";
  public static final String SUBJECT_NEW_EMPLOYEE = "Notification : About Employee Creation ";
  public static final String SUBJECT_NEW_INCOMING_SAMPLE = "Notification : About Incoming Sample Creation ";
  public static final String SUBJECT_FINISH_PRODUCT_SAMPLE = "Notification : About Finish Product Sample Creation ";
  public static final String SUBJECT_FINISH_PRODUCT_SAMPLE_ISSUE = "Notification : About Finish Product Sample Issue Creation ";
  public static final String SUBJECT_PLANT_EQUIPMENT = "Notification : About Plant Equipment Calibration ";
  public static final String SUBJECT_CUSTOMER = "Notification : About Customer Creation";
  public static final String SUBJECT_PLANT = "Notification : About Plant Creation";
  public static final String SUBJECT_RAW_MATERIAL = "Notification : About Raw Material Creation";
  public static final String SUBJECT_PROCESS_SAMPLE = "Notification : About Process Sample Creation";
  
  
  // for sample counts
  public static final String SAMPLE_COUNTS = "sampleCounts";
  // for plants
  public static final String PLANT_NAME = "PlantName";
  public static final String PLANT_ID = "PlantId";
  public static final String PLANTS = "plants";
  /*
   * Constants for Plant validation key & Success messages
   */
  public static final String ADD_PLANT_SUCCESS = "Plant Added Successfully";
  public static final String UPDATE_PLANT_SUCCESS = "Plant Updated Successfully";
  public static final String NO_DATA_FOUND = "No Data Found";
  public static final String PLANT_DELETED = "Plant Successfully Deleted";
  public static final String PLANT = "plant";
  /*
   * Constants for Designation validation key & Success messages
   */
  public static final String DESIGNATION_NAME = "designationName";
  public static final String DESIGNATION = "designation";
  public static final String DESIGNATIONS = "designations";
  public static final String ADD_DESIGNATION_SUCCESS = "Designation Added Successfully";
  public static final String DESIGNATION_DELETED = "Designation Successfully Deleted";
  public static final String UPDATE_DESIGNATION_SUCCESS = "Designation Updated Successfully";
  /*
   * Constants for Supplier Category validation key & Success messages
   */
  public static final String SUPPLIER_CATEGORY = "supplierCategory";
  public static final String SUPPLIER_CATEGORY_NAME = "category";
  public static final String ADD_SUPPLIER_CATEGORY_SUCCESS = "Supplier Category Added Successfully";
  public static final String DELETE_SUPPLIER_CATEGORY_SCCESS =
      "Supplier Category Successfully Deleted";
  public static final String UPDATE_SUPPLIER_CATEGORY_SUCCESS =
      "Supplier Category Updated Successfully";
  /*
   * Constants for Supplier validation key & Success messages
   */
  public static final String SUPPLIER_NAME = "name";
  public static final String EMAIL = "email";
  public static final String PHONE_NUMBER = "phoneNumber";
  public static final String ADD_SUPPLIER_SUCCESS = "Supplier Added Successfully";
  public static final String UPDATE_SUPPLIER_SUCCESS = "Supplier Updated Successfully";
  public static final String DELETE_SUPPLIER_SUCCESS = "Supplier Successfully Deleted";
  public static final String SUPPLIER = "Supplier";
  /*
   * Constants for Employee validation key & Success messages
   */
  public static final String EMPLOYEE_ID = "employeeId";
  public static final String EMPLOYEES = "employees";
  public static final String EMPLOYEE = "employee";
  public static final String EMPLOYEE_DELETED = "Employee Successfully Deleted";
  public static final String ADD_EMPLOYEE_SUCCESS = "Employee Added Successfully";
  public static final String UPDATE_EMPLOYEE_SUCCESS = "Employee Updated Successfully";
  /*
   * Constants for Pour validation key & Success messages
   */
  public static final String POUR = "pour";
  public static final String POURS = "pours";
  public static final String ADD_POUR_SUCCESS = "Pour Added Successfully";
  public static final String POUR_DELETED = "Pour Succeessfully Deleted";
  public static final String UPDATE_POUR_SUCCESS = "Pour Updated Successfully";
  /*
   * Constants for Unit validation key & Success messages
   */
  public static final String UNIT = "unit";
  public static final String UNITS = "units";
  public static final String ADD_UNIT_SUCCESS = "Unit Added Successfully";
  public static final String UNIT_DELETED = "Unit Successfully Deleted";
  public static final String UNIT_UPDATED_SUCCESS = "Unit Updated Successfully";

  /*
   * Constants for Parameter validation key & Success messages
   */
  public static final String PARAMETER_NAME = "parameterName";
  public static final String PARAMETER = "parameter";
  public static final String PARAMETERS = "parameters";
  public static final String PARAMETER_ID = "parameterId";
  public static final String PARAMETER_TYPE = "parameterType";
  public static final String PARAMETER_ABBREVIATION = "abbreviation";
  public static final String ADD_PARAMETER_SUCCESS = "Parameter Added Successfully";
  public static final String PARAMETER_DELETED = "Parameter Successfully Deleted";
  public static final String UPDATE_PARAMETER_SUCCESS = "Parameter Updated Successfully";
  /*
   * Constants for Plant Equipment Calibration validation key & Success messages
   */
  public static final String EQUIPMENT_PLANT_CALIBRATION = "plantEquipmentCalibration";
  public static final String EQUIPMENT_PLANT_CALIBRATIONS = "plantEquipmentCalibrations";
  public static final String DUE_DATE="dueDate";
  public static final String ADD_EQUIPMENT_PLANT_CALIBRATION_SUCCESS =
      "Plant Equipment Calibration Added Successfully";
  public static final String EQUIPMENT_PLANT_CALIBRATION_DELETED =
      "Plant Equipment Calibration Successfully Deleted";
  public static final String UPDATE_EQUIPMENT_PLANT_CALIBRATION_SUCCESS =
      "Plant Equipment Calibration Updated Successfully";
  /*
   * Constants for Equation validation key & Success messages
   */
  public static final String EQUATION_FORMULA = "formula";
  public static final String EQUATIONS = "equations";
  public static final String EQUATION = "equation";
  public static final String EQUATION_ID = "equationId";
  public static final String ADD_EQUATION_SUCCESS = "Equation Added Successfully";
  public static final String DELETE_EQUATION_SUCCESS = "Equation Successfully Deleted";
  public static final String UPDATE_EQUATION_SUCCESS = "Equation Updated Successfully";
  /*
   * Constants for Plant Equipment validation key & Success messages
   */
  public static final String PLANTEQUIPMENTS = "plantEquipments";
  public static final String PLANTEQUIPMENT = "plantEquipment";
  public static final String PLANTEQUIPMENT_SERIALNO = "plantEquipmentSerialNo";
  public static final String ADD_PLANTEQUIPMENT_SUCCESS = "Plant Equipment Added Successfully";
  public static final String PLANTEQUIPMENT_DELETED = "Plant Equipment Successfully Deleted";
  public static final String UPDATE_PLANTEQUIPMENT_SUCCESS = "Plant Equipment Updated Successfully";
  /*
   * Constants for Customer validation key & Success messages
   */
  public static final String CUSTOMERS = "customers";
  public static final String CUSTOMER = "customer";
  public static final String CUSTOMER_ID = "customerId";
  public static final String ADD_CUSTOMER_SUCCESS = "Customer Added Successfully";
  public static final String CUSTOMER_DELETED = "Customer Successfully Deleted";
  public static final String UPDATE_CUSTOMER_SUCCESS = "Customer Updated Successfully";
  /*
   * Constants for Material Sub Category validation key & Success messages
   */
  public static final String MATERIAL_SUB_CATEGORY_ID = "materialSubCategoryId";
  public static final String MATERIAL_SUB_CATEGORIES = "materialSubCategories";
  public static final String MATERIAL_SUB_CATEGORY = "materialSubCategory";
  public static final String MATERIAL_SUB_CATEGORY_NAME = "name";
  public static final String ADD_MATERIAL_SUB_CATEGORY_SUCCESS =
      "Material Subcategory Added Successfully";
  public static final String UPDATE_MATERIAL_SUB_CATEGORY_SUCCESS =
      "Material Subcategory Updated Successfully";
  public static final String DELETE_MATERIAL_SUB_CATEGORY_SUCCESS =
      "Material Subcategory Successfully Deleted";
  /*
   * Constants for Material Category validation key & Success messages
   */
  public static final String MATERIAL_CATEGORY_NAME = "name";
  public static final String MATERIAL_CATEGORIES = "materialCategories";
  public static final String MATERIAL_CATEGORY = "materialCategory";
  public static final String MATERIAL_CATEGORY_ID = "materialCategoryId";
  public static final String ADD_MATERIAL_CATEGORY_SUCCESS = "Material Category Added Successfully";
  public static final String MATERIAL_CATEGORY_DELETED = "Material Category Successfully Deleted";
  public static final String UPDATE_MATERIAL_CATEGORY_SUCCESS =
      "Material Category Updated Successfully";
  /*
   * Constants for Test validation key & Success messages
   */
  public static final String TEST_CONFIGURE = "testConfigure";
  public static final String TEST_TYPE = "testType";
  public static final String TEST_CONFIGURE_ID = "testConfigureId";
  public static final String ADD_TEST_CONFIGURE_SUCCESS = "Test Configure Added Successfully";
  public static final String UPDATE_TEST_CONFIGURE_SUCCESS = "Test Configure Updated Successfully";
  public static final String DELETE_TEST_CONFIGURE_SCCESS = "Test Configure Successfully Deleted";
  /*
   * Constants for Material State validation key & Success messages
   */
  public static final String MATERIAL_STATE = "materialState";
  public static final String MATERIAL_STATE_ID = "id";
  public static final String ADD_MATERIAL_STATE_SUCCESS = "Material State Added Successfully";
  public static final String UPDATE_MATERIAL_STATE_SUCCESS = "Material State Updated Successfully";
  public static final String DELETE_MATERIAL_STATE_SCCESS = "Material State Successfully Deleted";
  /*
   * Constants for Raw Material validation key & Success messages
   */
  public static final String RAW_MATERIAL_NAME = "name";
  public static final String RAW_MATERIAL = "rawMaterial";
  public static final String RAW_MATERIAL_ID = "id";
  public static final String ADD_RAW_MATERIAL_SUCCESS = "Raw Material Added Successfully";
  public static final String UPDATE_RAW_MATERIAL_SUCCESS = "Raw Material Updated Successfully";
  public static final String DELETE_RAW_MATERIAL_SCCESS = "Raw Material Successfully Deleted";
  /*
   * Constants for Equipment validation key & Success messages
   */
  public static final String EQUIPMENT_NAME = "name";
  public static final String EQUIPMENTS = "equipments";
  public static final String EQUIPMENMT_ID = "equipmentId";
  public static final String EQUIPMENT = "equipment";
  public static final String ADD_EQUIPMENT_SUCCESS = "Equipment Added Successfully";
  public static final String EQUIPMENT_DELETED = "Equipment Successfully Deleted";
  public static final String UPDATE_EQUIPMENT_SUCCESS = "Equipment Updated Successfully";
  /*
   * Constants for Incoming Sample validation key & Success messages
   */
  public static final String INCOMING_SAMPLE_CODE = "incomingSampleCode";
  public static final String INCOMING_SAMPLE = "incomingSample";
  public static final String INCOMING_SAMPLES = "incomingSamples";
  public static final String INCOMING_SAMPLE_STATUS = "status";
  public static final String INCOMING_SAMPLE_DELETED = "Incoming Sample Successfully Deleted";
  public static final String ADD_INCOMING_SAMPLE_SUCCESS = "Incoming Sample Added Successfully";
  public static final String UPDATE_INCOMING_SAMPLE_SUCCESS =
      "Incoming Sample Updated Successfully";
  public static final String UPDATE_STATUS_INCOMING_SAMPLE_SUCCESS =
      "Incoming Sample Status Updated Successfully";
  /*
   * Constants for Project validation key & Success messages
   */
  public static final String PROJECT_CODE = "projectcode";
  public static final String PROJECT = "project";
  public static final String PROJECTS = "projects";
  public static final String PROJECT_NAME = "name";
  public static final String PROJECT_DELETED = "Project Successfully Deleted";
  public static final String ADD_PROJECT_SUCCESS = "Project Added Successfully";
  public static final String UPDATE_PROJECT_SUCCESS = "Project Updated Successfully";
  /*
   * Constants for Mix Design validation key & Success messages
   */
  public static final String ADD_MIX_DESIGN_SUCCESS = "Mix Design Added Successfully";
  public static final String MIX_DESIGN = "mixDesign";
  public static final String MIX_DESIGNS = "mixDesigns";
  public static final String MIX_DESIGN_DELETED = "Mix Design Successfully Deleted";
  public static final String MIX_DESIGN_CODE = "mixDesignCode";
  public static final String UPDATE_MIX_DESIGN_SUCCESS = "Mix Design Updated Successfully";
  /*
   * Constants for Process Sample Load validation key & Success messages
   */
  public static final String PROCESS_SAMPLE_LOAD_ID = "processSampleLoadId";
  public static final String PROCESS_SAMPLE_LOAD = "processSampleLoad";
  public static final String PROCESS_SAMPLE_LOADS = "processSampleLoads";
  public static final String PROCESS_SAMPLE_LOAD_DELETED =
      "Process Sample Load Successfully Deleted";
  public static final String ADD_PROCESS_SAMPLE_LOAD_SUCCESS =
      "Process Sample Load Added Successfully";
  public static final String UPDATE_PROCESS_SAMPLE_LOAD_SUCCESS =
      "Process Sample Load Updated Successfully";
  /*
   * Constants for Test Parameter validation key & Success messages
   */
  public static final String TEST_PARAMETERS = "testparameters";
  public static final String TEST_PARAMETER = "testparameter";
  public static final String TEST_PARAMETER_ID = "testParameterId";
  public static final String ADD_TEST_PARAMETER_SUCCESS = "Test Parameter Added Successfully";
  public static final String TEST_PARAMETER_DELETED = "Test Parameter Successfully Deleted";
  public static final String UPDATE_TEST_PARAMETER_SUCCESS = "Test Parameter Updated Successfully";
  public static final String ABBREVIATION = "abbreviation";

  /*
   * Constants for Process Sample validation key & Success messages
   */
  public static final String PROCESS_SAMPLE_CODE = "processSampleCode";
  public static final String PROCESS_SAMPLE = "processSample";
  public static final String PROCESS_SAMPLES = "processSamples";
  public static final String PROCESS_SAMPLE_DELETED = "Process Sample Successfully Deleted";
  public static final String ADD_PROCESS_SAMPLE_SUCCESS = "Process Sample Added Successfully";
  public static final String UPDATE_PROCESS_SAMPLE_SUCCESS = "Process Sample Updated Successfully";
  /*
   * Constants for Mix Design Proportion validation key & Success messages
   */
  public static final String MIX_DESIGN_PROPORTION = "mixDesignProportion";
  public static final String MIX_DESIGN_PROPORTIONS = "mixDesignProportions";
  public static final String MIX_DESIGN_PROPORTION_ID = "mixDesignProportionId";
  public static final String ADD_MIX_DESIGN_PROPORTION_SUCCESS =
      "Mix Design Proportion Added Successfully";
  public static final String MIX_DESIGN_PROPORTION_DELETED =
      "Mix Design Proportion Successfully Deleted";
  public static final String UPDATE_MIX_DESIGN_PROPORTION_SUCCESS =
      "Mix Design Proportion Updated Successfully";
  /*
   * Constants for Admixture Accepted Value validation key & Success messages
   */
  public static final String ADMIXTURE_ACCEPTED_VALUE_ID = "admixtureAcceptedValueId";
  public static final String ADMIXTURE_ACCEPTED_VALUE = "admixtureAcceptedValue";
  public static final String ADMIXTURE_ACCEPTED_VALUES = "admixtureAcceptedValues";
  public static final String ADMIXTURE_ACCEPTED_VALUE_DELETED =
      "Admixture Accepted Value Successfully Deleted";
  public static final String ADD_ADMIXTURE_ACCEPTED_VALUE_SUCCESS =
      "Admixture Accepted Value Added Successfully";
  public static final String UPDATE_ADMIXTURE_ACCEPTED_VALUE_SUCCESS =
      "Admixture Accepted Value Updated Successfully";
  /*
   * Constants for Material Accepted Value validation key & Success messages
   */
  public static final String MATERIAL_ACCEPTED_VALUE_ID = " materialAcceptedValueId";
  public static final String MATERIAL_ACCEPTED_VALUE = "materialAcceptedValue";
  public static final String MATERIAL_ACCEPTED_VALUES = "materialAcceptedValues";
  public static final String MATERIAL_ACCEPTED_VALUE_DELETED =
      "Material Accepted Value Successfully Deleted";
  public static final String ADD_MATERIAL_ACCEPTED_VALUE_SUCCESS =
      "Material Accepted Value Added Successfully";
  public static final String UPDATE_MATERIAL_ACCEPTED_VALUE_SUCCESS =
      "Material Accepted Value Updated Successfully";
  /*
   * Constants for Accepted Value validation key & Success messages
   */
  public static final String ACCEPTED_VALUE = "acceptedValue";
  public static final String ACCEPTED_VALUES = "acceptedValues";
  public static final String ACCEPTED_VALUE_ID = "acceptedValueId";
  public static final String ACCEPTED_VALUE_TEST_CONFIGURE_ID = "acceptedValueTestConfigureId";
  public static final String ACCEPTED_VALUE_DELETED = "Accepted Value Successfully Deleted";
  public static final String ADD_ACCEPTED_VALUE_SUCCESS = "Accepted Value Added Successfully";
  public static final String ACCEPTED_VALUE_UPDATE_SUCCESS = "Accepted Value Updated Successfully";
  /*
   * Constants for Material Test validation key & Success messages
   */
  public static final String MATERIAL_TEST = "materialTest";
  public static final String MATERIAL_TESTS = "materialTests";
  public static final String MATERIAL_TEST_ID = "materialTestId";
  public static final String MATERIAL_TEST_CODE = "materialTestCode";
  public static final String MATERIAL_TEST_STATUS = "materialTestStaus";
  public static final String ADD_MATERIAL_TEST_SUCCESS = "Material Test Added Successfully";
  public static final String MATERIAL_TEST_DELETED = "Material Test Successfully Deleted";
  public static final String MATERIAL_TEST_UPDATED = "Material Test Updated Successfully";
  /*
   * Constants for Material Test Trial validation key & Success messages
   */
  public static final String MATERIAL_TEST_TRIAL = "materialTestTrial";
  public static final String MATERIAL_TEST_TRIALS = "materialTestTrials";
  public static final String MATERIAL_TEST_TRIAL_CODE = "materialTestTrialCode";
  public static final String ADD_MATERIAL_TEST_TRIAL_SUCCESS =
      "Material Test Trial Added successfully";
  public static final String MATERIAL_TEST_TRIAL_DELETED =
      "Material Test Trial Succeessfully Deleted";
  public static final String UPDATE_MATERIAL_TEST_TRIAL_SUCCESS =
      "Material Test Trial Updated Successfully";
  public static final String UPDATE_MATERIAL_TEST_TRIAL_AVERAGE_SUCCESS =
      "Material Test Trial Average & Status Updated Successfully";
  /*
   * Constants for Parameter Result validation key & Success messages
   */
  public static final String PARAMETER_RESULT = "parameterResult";
  public static final String PARAMETER_RESULTS = "parameterResults";
  public static final String PARAMETER_RESULT_ID = "parameterResultId";
  public static final String PARAMETER_VALUE_ADDED_AND_RESULT_UPDATED =
      "Parameter Values Added & Result Updated Successfully";
  public static final String PARAMETER_RESULT_DELETED = "Parameter Result Successfully Deleted";
  public static final String UPDATE_PARAMETER_RESULT_SUCCESS =
      "Parameter Result Updated Successfully";
  public static final String RESULT_SUCCESSFULLY_UPDATED = "Result Updated Successfully";
  /*
   * Constants for Sieve Test Trial validation key & Success messages
   */
  public static final String TWO_DECIMAL_FORMAT = "#.##";
  public static final String DECIMAL_FORMAT = "#.##";
  public static final String SIEVE_TEST_TRIALS = "sieveTestTrials";
  public static final String SIEVE_TEST_TRIAL_ID = "id";
  public static final String ADD_SIEVE_TEST_TRIAL_SUCCEESS = "Sieve Test Trial Added Successfully";
  public static final String DELETE_SIEVE_TEST_TRIAL_SUCCESS =
      "Sieve Test Trial Successfully Deleted";
  /*
   * Constants for Sieve Test validation key & Success messages
   */
  public static final String SIEVE_TEST_CODE = "sieveTestCode";
  public static final String SIEVE_TEST = "sieveTest";
  public static final String SIEVE_TESTS = "sieveTests";
  public static final String ADD_SIEVE_TEST_SUCCESS = "Sieve Test Added Successfully";
  public static final String SIEVE_TEST_DELETED = "Sieve Test Successfully Deleted";
  public static final String UPDATE_SIEVE_TEST_SUCCESS = "SieveT est Updated Successfully";
  /*
   * Constants for Fineness Modulus validation key & Success messages
   */
  public static final String FINENESS_MODULUS_ID = "finenessModulusId";
  public static final String FINENESS_MODULUS = "finenessModulus";
  public static final String ADD_FINENESS_MODULUS_SUCCESS = "Fineness Modulus Added Successfully";
  public static final String FINENESS_MODULUS_DELETED = "Fineness Modulus Successfully Deleted";
  public static final String UPDATE_FINENESS_MODULUS_SUCCESS =
      "Fineness Modulus Updated Successfully";
  /*
   * Finish Product Sample
   */
  public static final String FINISH_PRODUCT_CODE = "finishProductCode";
  public static final String FINISH_PRODUCT_SAMPLE_ID = "finishProductSampleId";
  public static final String FINISH_PRODUCT_SAMPLE = "finishProductSample";
  public static final String FINISH_PRODUCT_SAMPLES = "finishProductSamples";
  public static final String ADD_FINISH_PRODUCT_SAMPLE_SUCCESS =
      "Finished Product Sample Added Successfully";
  public static final String FINISH_PRODUCT_SAMPLE_DELETED =
      "Finished Product Sample Successfully Deleted";
  public static final String UPDATE_FINISH_PRODUCT_SAMPLE_SUCCESS =
      "Finished Product Sample Updated Successfully";

  /*
   * Constants for Test validation key & Success messages
   */
  public static final String TEST = "test";
  public static final String TEST_ID = "testId";
  public static final String ADD_TEST_SUCCESS = "Test Added Successfully";
  public static final String UPDATE_TEST_SUCCESS = "Test Updated Successfully";
  public static final String DELETE_TEST_SCCESS = "Test successfully Deleted";

  /*
   * Constants for Mix Design validation key & Success messages
   */

  public static final String ADD_FINISH_PRODUCT_SAMPLE_ISSUE_SUCCESS =
      "Finish Product Sample Issue Added Successfully";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE = "finishProductSampleIssue";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES = "finishProductSampleIssues";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_DELETED =
      "Finish Product Sample Issue Successfully Deleted";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_ID = "finishProductSampleIssueId";
  public static final String UPDATE_FINISH_PRODUCT_SAMPLE_ISSUE_SUCCESS =
      "Finish Product Sample Issue Updated Successfully";
  /*
   * Constants for Quality Parameter validation key & Success messages
   */
  public static final String QUALITY_PARAMETER = "qualityParameter";
  public static final String QUALITY_PARAMETERS = "qualityParameters";
  public static final String QUALITY_PARAMETER_ID = "qualityParameterId";
  public static final String ADD_QUALITY_PARAMETER_SUCCESS = "Quality Parameter Added Successfully";
  public static final String DELETED_QUALITY_PARAMETER = "Quality Parameter Successfully Deleted";
  public static final String UPDATE_QUALITY_PARAMETER_SUCCESS =
      "Quality Parameter Updated Successfully";
  /*
   * Constants for report
   */
  public static final String TEST_REPORT = "testReport";
  public static final String TEST_DETAIL_REPORT = "testDetailReport";
  public static final String CONCRETE_TYPE = "concreteType";
  public static final String REPORT_SUCESS = "Sucessfully send e-mail for report";
  public static final String REPORT_SEND_ERROR = "Unable to send email";
  public static final String CONCRETE_STRENGTH = "concreteStrength";

  /*
   * Constants for Parameter Equation validation key & Success messages
   */
  public static final String PARAMETER_EQUATION = "parameterEquation";
  public static final String PARAMETER_EQUATION_ID = "parameterEquationId";
  public static final String PARAMETER_EQUATIONS = "parameterEquations";
  public static final String ADD_PARAMETER_EQUATION_SUCCESS =
      "Parameter Equation Added Successfully";
  public static final String DELETE_PARAMETER_EQUATION_SCCESS =
      "Parameter Equation Successfully Deleted";
  public static final String UPDATE_PARAMETER_EQUATION_SUCCESS =
      "Parameter Equation Updated Successfully";

  /*
   * Constants for Parameter Equation Element validation key & Success messages
   */
  public static final String PARAMETER_EQUATION_ELEMENT = "parameterEquationElement";
  public static final String PARAMETER_EQUATION_ELEMENT_ID = "parameterEquationElementId";
  public static final String PARAMETER_EQUATION_ELEMENTS = "parameterEquationElements";
  public static final String ADD_PARAMETER_EQUATION_ELEMENT_SUCCESS =
      "Parameter Equation Element Added Successfully";
  public static final String DELETE_PARAMETER_EQUATION_ELEMENT_SCCESS =
      "Parameter Equation Element Successfully Deleted";
  public static final String UPDATE_PARAMETER_EQUATION_ELEMENT_SUCCESS =
      "Parameter Equation Element Updated Successfully";

  public static final String SIEVETEST = "SieveTest";

  /*
   * Constants for Finish Product Trial validation key & Success messages
   */
  public static final String FINISH_PRODUCT_TRIAL = "finishProductTrial";
  public static final String FINISH_PRODUCT_TRIALS = "finishProductTrials";
  public static final String FINISH_PRODUCT_TRIAL_ID = "finishProductTrialId";
  public static final String ADD_FINISH_PRODUCT_TRIAL_SUCCESS =
      "Finish Produt Trial Added Successfully";
  public static final String DELETE_FINISH_PRODUCT_TRIAL_SUCCESS =
      "Finish Product Trial Sucessfully Deleted";
  public static final String UPDATE_FINISH_PRODUCT_TRIAL_SUCCESS =
      "Finish Product Trial Updated Successfully";
  /*
   * Constants for Finish Product Parameter Result validation key & Success messages
   */
  public static final String FINISH_PRODUCT_PARAMETER_RESULTS = "finishProductParameterResults";
  /*
   * Constants for Finish Product Test validation key & Success messages
   */
  public static final String FINISH_PRODUCT_TEST = "finishProductTest";
  public static final String FINISH_PRODUCT_TESTS = "finishProductTests";
  public static final String FINISH_PRODUCT_TEST_ID = "finishProductTestId";
  public static final String ADD_FINISH_PRODUCT_TEST_SUCCESS = "Finish Product Added Successfully";
  public static final String DELETED_FINISH_PRODUCT_TEST = "Finish Product Successfully Deleted";
  public static final String UPDATE_FINISH_PRODUCT_TEST_SUCCESS =
      "Finish Product Updated Successfully";
  public static final String FINISH_PRODUCT_TEST_STATUS = "finishProductTestStatus";

  public static final String ADD_EMAIL_RECIPIENT_SUCCESS = "Email Recipient Added Successfully";
  public static final String EMAIL_RECIPIENT = "emailRecipient";
  public static final String EMAIL_RECIPIENTS = "emailRecipients";
  public static final String EMAIL_RECIPIENT_DELETED = "Email Recipient Successfully Deleted";
  public static final String EMAIL_RECIPIENT_ID = "emailRecipientId";


  public static final String EMAIL_GROUPS = "emailGroups";
  public static final String EMAIL_GROUP = "emailGroup";
  public static final String ADD_EMAIL_GROUP_SUCCESS = "Email Group Added Successfully";
  public static final String UPDATE_EMAIL_GROUP_SUCCESS = "Email Group Updated Successfully";
  public static final String EMAIL_GROUP_DELETED = "Email Group Successfully Deleted";
  public static final String EMAIL_GROUP_ID = "emailGroupId";
  public static final String EMAIL_GROUP_PLANT_EQUIPMENT_CALIBRATION = "Calibration Group";
  public static final String EMAIL_GROUP_MIX_DESIGN = "Mix Design Group";
  public static final String EMAIL_GROUP_INCOMING_SAMPLE_STATUS = "Incoming Sample Group";
  

  /* email notification days */
  public static final String ADD_EMAIL_NOTIFICATION_DAYS =
      "Email Notification Days Added Successfully";
  public static final String UPDATE_EMAIL_NOTIFICATION_DAYS =
      "Email Notification Days Updated Successfully";
  public static final String EMAIL_NOTIFICATION_DAYS = "EmailNotificationDays";
  public static final String EMAIL_NOTIFICATION_DAY = "EmailNotificationDay";
  public static final String EMAIL_NOTIFICATION_DAY_DELETED =
      "Email Notification Days Successfully Deleted";
  public static final String EMAIL_NOTIFICATION_DAY_ID = "emailNotificationDayId";
  
  /* email points */
  public static final String EMAIL_POINT = "emailPoint";
  public static final String EMAIL_POINTS = "emailPoints";
  public static final String EMAIL_POINT_ID = "emailPointId";
  public static final String ADD_EMAIL_POINT_SUCCESS = "Email Points Added Successfully";
  public static final String UPDATE_EMAIL_POINT_SUCCESS = "Email Points Updated Successfully";
  public static final String EMAIL_POINTS_STATUS_ACTIVE = "Email Points is Inactive";
  
 
 
  /*
   * Constants for Test Equation validation key & Success messages
   */
  public static final String TEST_EQUATION = "testEquation";
  public static final String TEST_EQUATIONS = "testEquations";
  public static final String TEST_EQUATION_ID = "testEquationId";
  public static final String ADD_TEST_EQUATION_SUCCESS = "Test Equation Added Successfully";
  public static final String UPDATE_TEST_EQUATION_SUCCESS = "Test Equation Updated Successfully";
  public static final String DELETE_TEST_EQUATION_SCCESS = "Test Equation successfully Deleted";

  /*
   * Constants for Test Equation Parameter validation key & Success messages
   */
  public static final String TEST_EQUATION_PARAMETERS = "testEquations";


  /*
   * Encapsulate constructor to restrict modification from outside
   */
  private Constants() {}
}
