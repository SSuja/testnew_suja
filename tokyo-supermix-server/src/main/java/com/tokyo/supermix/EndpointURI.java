package com.tokyo.supermix;

/**
 * Contains all the rest endpoint url constants
 */
public final class EndpointURI {
  private static final String BASE_API_PATH = "/api/v1/";
  /*
   * private constructor to avoid instantiating this class.
   */
  private static final String SLASH = "/";
  private static final String CODE = "/{code}";
  private static final String ID = "/{id}";
  private static final String SEARCH = "/search";
  private static final String SERIALNO = "/{serialNo}";
  private static final String PLANT_WISE = "/plant-wise" + SLASH + "{plantCode}";
  private static final String PLANT_WISE_SEARCH = "/search" + SLASH + "{plantCode}";
  /*
   * Plant apis
   */
  public static final String PLANT = BASE_API_PATH + "plant";
  public static final String PLANT_BY_CODE = PLANT + CODE;
  public static final String PLANTS = BASE_API_PATH + "plants";
  public static final String SEARCH_PLANT = PLANT + SEARCH;

  /*
   * Designation API
   */
  public static final String DESIGNATION = BASE_API_PATH + "designation";
  public static final String DESIGNATIONS = BASE_API_PATH + "designations";
  public static final String DESIGNATION_BY_ID = DESIGNATION + ID;

  /*
   * Supplier Category apis
   */
  public static final String SUPPLIER_CATEGORY = BASE_API_PATH + "supplier-category";
  public static final String SUPPLIER_CATEGORY_BY_ID = SUPPLIER_CATEGORY + ID;
  public static final String SUPPLIER_CATEGORIES = BASE_API_PATH + "supplier-categories";

  /*
   * Supplier Apis
   */
  public static final String SUPPLIER = BASE_API_PATH + "supplier";
  public static final String SUPPLIERS = BASE_API_PATH + "suppliers";
  public static final String SUPPLIER_BY_ID = SUPPLIER + ID;
  public static final String GET_SUPPLIER_BY_SUPPLIER_CATEGORY_ID =
      BASE_API_PATH + "supplier-category" + SLASH + "{suppilerCategoryId}" + SLASH + "plant" + SLASH
          + "{plantCode}";
  public static final String SUPPLIER_SEARCH = SUPPLIER + SEARCH;
  public static final String GET_SUPPLIERS_BY_PLANT_CODE =
      SUPPLIERS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String SUPPLIER_BY_PLANT = SUPPLIER + PLANT_WISE;
  public static final String GET_SUPPLIERS_BY_PLANT_CODE_AND_SUPPLIER_CATEGORY =
      SUPPLIERS + SLASH + "plant" + SLASH + "{plantCode}" + SLASH + "supplier-category" + SLASH
          + "{supplierCategoryId}";

  /*
   * Employee apis
   */
  public static final String EMPLOYEE = BASE_API_PATH + "employee";
  public static final String EMPLOYEE_BY_ID = EMPLOYEE + ID;
  public static final String EMPLOYEES = BASE_API_PATH + "employees";
  public static final String SEARCH_EMPLOYEE = EMPLOYEE + SEARCH;
  public static final String GET_EMPLOYEES_BY_PLANT_CODE =
      EMPLOYEES + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String EMPLOYEE_BY_PLANT = EMPLOYEE + PLANT_WISE;
  public static final String EMPLOYEE_WITH_TOKEN =
      EMPLOYEE + SLASH + "confirmation" + SLASH + "{confirmationToken}";
  /*
   * Material Category APIs
   */
  public static final String MATERIAL_CATEGORY = BASE_API_PATH + "material-category";
  public static final String MATERIAL_CATEGORIES = BASE_API_PATH + "material-categories";
  public static final String MATERIAL_CATEGORY_BY_ID = MATERIAL_CATEGORY + ID;
  public static final String MATERIAL_CATEGORY_BY_MAIN_TYPE =
      MATERIAL_CATEGORY + SLASH + "main-type" + SLASH + "{mainType}";
  /*
   * Pour APIs
   */
  public static final String POUR = BASE_API_PATH + "pour";
  public static final String POURS = BASE_API_PATH + "pours";
  public static final String POUR_BY_ID = POUR + ID;
  public static final String POUR_SEARCH = POUR + SEARCH;
  public static final String GET_POURS_BY_PLANT_CODE =
      POURS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String POUR_BY_PLANT = POUR + PLANT_WISE;
  /*
   * Unit apis
   */
  public static final String UNIT = BASE_API_PATH + "unit";
  public static final String UNITS = BASE_API_PATH + "units";
  public static final String UNIT_BY_ID = UNIT + ID;

  /*
   * MaterialSubCategory Apis
   */
  public static final String MATERIAL_SUB_CATEGORY = BASE_API_PATH + "material-sub-category";
  public static final String MATERIAL_SUB_CATEGORIES = BASE_API_PATH + "material-sub-categories";
  public static final String MATERIAL_CATEGORY_ID = "/{materialCategoryId}";
  public static final String GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY =
      MATERIAL_SUB_CATEGORY + "/category" + MATERIAL_CATEGORY_ID;
  public static final String MATERIAL_SUB_CATEGORY_BY_ID = MATERIAL_SUB_CATEGORY + ID;
  public static final String GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY_NAME =
      MATERIAL_SUB_CATEGORY + "/materialCategoryName" + "/{materialCategoryName}";
  public static final String MATERIAL_SUB_CATEGORY_SEARCH = MATERIAL_SUB_CATEGORY + SEARCH;

  /*
   * Equipment APIs
   */
  public static final String EQUIPMENT = BASE_API_PATH + "equipment";
  public static final String EQUIPMENTS = BASE_API_PATH + "equipments";
  public static final String DELETE_EQUIPMENT = EQUIPMENT + ID;
  public static final String GET_EQUIPMENT_BY_ID = EQUIPMENT + ID;
  /*
   * Test Configure
   */
  public static final String TEST_CONFIGURE = BASE_API_PATH + "test-configure";
  public static final String TEST_CONFIGURES = BASE_API_PATH + "test-configures";
  public static final String TEST_CONFIGURE_BY_ID = TEST_CONFIGURE + ID;
  public static final String GET_TEST_CONFIGURE_BY_TEST_TYPE =
      TEST_CONFIGURE + SLASH + "test-type" + SLASH + "{testType}";
  public static final String GET_TEST_CONFIGURE_BY_CORE_TEST =
      TEST_CONFIGURE + SLASH + "core-test" + SLASH + "{coreTest}";
  public static final String TEST_CONFIGURE_BY_ID_AND_CORE_TEST =
      TEST_CONFIGURE + SLASH + "core-test";
  public static final String TEST_CONFIGURE_SEARCH = TEST_CONFIGURE + SEARCH;
  public static final String GET_TEST_CONFIGURE_BY_MATERIAL_SUB_CATEGORY =
      TEST_CONFIGURE + SLASH + "material-sub-category" + SLASH + "{materialSubCategoryId}";
  public static final String GET_TEST_CONFIGURE_BY_MATERIAL_SUB_CATEGORY_AND_TEST_TYPE =
      TEST_CONFIGURE + SLASH + "material-sub-category" + SLASH + "{testType}" + SLASH
          + "{materialSubCategoryId}";
  public static final String UPDATE_ACCEPTED_TYPE_TEST_CONFIGURE = TEST_CONFIGURE + SLASH
      + "{testConfigureId}" + SLASH + "accepted-type" + SLASH + "{acceptedType}";
  /*
   * Material State
   */
  public static final String MATERIAL_STATE = BASE_API_PATH + "material-state";
  public static final String MATERIAL_STATES = BASE_API_PATH + "material-states";
  public static final String MATERIAL_STATE_BY_ID = MATERIAL_STATE + ID;
  /*
   * Raw Material apis
   */
  public static final String RAW_MATERIAL = BASE_API_PATH + "raw-material";
  public static final String RAW_MATERIALS = BASE_API_PATH + "raw-materials";
  public static final String GET_RAW_MATERIAL_BY_ID = RAW_MATERIAL + ID;
  public static final String DELETE_RAW_MATERIAL = RAW_MATERIAL + ID;
  public static final String SEARCH_RAW_MATERIAL = RAW_MATERIAL + SEARCH;
  public static final String ACTIVE_RAW_MATERIALS =
      BASE_API_PATH + "active" + SLASH + "raw-materials";
  public static final String GET_BY_MATERIAL_SUB_CATEGORY =
      RAW_MATERIAL + SLASH + "material-sub-category" + SLASH + "{materialSubCategoryId}";
  public static final String RAW_MATERIALS_BY_PLANT = RAW_MATERIAL + PLANT_WISE;
  public static final String RAW_MATERIALS_BY_MATERIAL_SUBCATORY_AND_PLANT = RAW_MATERIAL + SLASH
      + "material-sub-category" + SLASH + "{materialSubCategoryId}" + PLANT_WISE;
  /*
   * EquipmentPlantCalibration apis
   */
  public static final String EQUIPMENT_PLANT_CALIBRATION =
      BASE_API_PATH + "plant-equipment-calibration";
  public static final String EQUIPMENT_PLANT_CALIBRATIONS =
      BASE_API_PATH + "plant-equipment-calibrations";
  public static final String EQUIPMENT_PLANT_CALIBRATION_BY_ID = EQUIPMENT_PLANT_CALIBRATION + ID;
  public static final String EQUIPMENT_PLANT_CALIBRATION_SEARCH =
      EQUIPMENT_PLANT_CALIBRATION + SEARCH;
  public static final String GET_EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT_CODE =
      EQUIPMENT_PLANT_CALIBRATIONS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT =
      EQUIPMENT_PLANT_CALIBRATION + PLANT_WISE;
  /*
   * PlantEquipment
   * 
   */
  public static final String PLANT_EQUIPMENT = BASE_API_PATH + "plantequipment";
  public static final String PLANT_EQUIPMENTS = BASE_API_PATH + "plantequipments";
  public static final String PLANTEQUIPMENT_BY_SERIALNO = PLANT_EQUIPMENT + SERIALNO;
  public static final String PLANTEQUIPMENT_SEARCH = PLANT_EQUIPMENT + SEARCH;
  public static final String GET_PLANTEQUIPMENTS_BY_PLANT_CODE =
      PLANT_EQUIPMENTS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String PLANT_EQUIPMENTS_BY_PLANT = PLANT_EQUIPMENT + PLANT_WISE;
  /*
   * Customer apis
   */
  public static final String CUSTOMER = BASE_API_PATH + "customer";
  public static final String CUSTOMERS = BASE_API_PATH + "customers";
  public static final String CUSTOMER_BY_ID = CUSTOMER + ID;
  public static final String CUSTOMER_SEARCH = CUSTOMER + SEARCH;
  public static final String GET_CUSTOMERS_BY_PLANT_CODE =
      CUSTOMERS + PLANT_WISE_SEARCH + SLASH + "customerName";
  public static final String GET_TEST_DETAILS_BY_CONFIGURE_ID =
      TEST_CONFIGURE + SLASH + "testDetails" + SLASH + "{testConfigId}";
  public static final String CUSTOMER_BY_PLANT = CUSTOMER + PLANT_WISE;
  public static final String PLANT_EQUIPMENTS_BY_CALIBRATION_TRUE_AND_EQUIPMENTID =
      PLANT_EQUIPMENT + SLASH + "equipment" + SLASH + "{equipmentId}" + SLASH + "{plantCode}";
  /*
   * Equation APIs
   */
  public static final String EQUATION = BASE_API_PATH + "equation";
  public static final String EQUATIONS = BASE_API_PATH + "equations";
  public static final String EQUATION_BY_ID = EQUATION + ID;
  public static final String EQUATIONS_BY_EQUATION_TYPE =
      EQUATION + SLASH + "equation-type" + SLASH + "{equationType}";
  public static final String EQUATION_BY_EQUATION_NAME =
      EQUATION + SLASH + "equation-name" + SLASH + "{equationName}";
  public static final String EQUATION_BY_EQUATION_EXISTS_TRUE =
      EQUATION + SLASH + "equation-exists-true";
  public static final String UPADTE_TEST_CONFIGURE_EQUATION =
      EQUATION + SLASH + "update-equation" + SLASH + "{testConfigureId}";
  /*
   * Parameter apis
   * 
   */
  public static final String PARAMETER = BASE_API_PATH + "parameter";
  public static final String PARAMETERS = BASE_API_PATH + "parameters";
  public static final String PARAMETER_BY_ID = PARAMETER + ID;
  public static final String PARAMETER_SEARCH = PARAMETER + SEARCH;
  public static final String PARAMETER_BY_PARAMETER_TYPE =
      PARAMETER + SLASH + "parameter-type" + SLASH + "{parameterType}";

  /*
   * Project APIs
   */
  public static final String PROJECT = BASE_API_PATH + "project";
  public static final String PROJECT_BY_ID = PROJECT + CODE;
  public static final String PROJECTS = BASE_API_PATH + "projects";
  public static final String SEARCH_PROJECT = PROJECT + SEARCH;
  public static final String GET_PROJECTS_BY_PLANT_CODE =
      PROJECTS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String PROJECT_BY_PLANT = PROJECT + PLANT_WISE;

  public static final String GET_PROJECTS_BY_CUSTOMER =
      PROJECTS + SLASH + "customer" + SLASH + "{customerId}";

  public static final String GET_PROJECTS_BY_CUSTOMER_PLANT_WISE =
      PROJECTS + SLASH + "customer" + SLASH + "{customerId}" + PLANT_WISE;  
  public static final String GET_PROJECT_BY_NAME =
      PROJECTS + PLANT_WISE_SEARCH + SLASH + "projectName";
  /*
   * IncomingSample Apis
   */
  public static final String INCOMING_SAMPLE = BASE_API_PATH + "incoming-sample";
  public static final String INCOMING_SAMPLES = BASE_API_PATH + "incoming-samples";
  public static final String INCOMING_SAMPLE_BY_STATUS =
      INCOMING_SAMPLE + "/test-status" + "/{status}";
  public static final String INCOMING_SAMPLE_BY_CODE = INCOMING_SAMPLE + CODE;
  public static final String INCOMING_SAMPLE_BY_CODE_AND_STATUS =
      INCOMING_SAMPLE_BY_CODE + "/test-status" + "/{status}";
  public static final String INCOMING_SAMPLE_SEARCH = INCOMING_SAMPLE + SEARCH;
  public static final String INCOMING_SAMPLES_BY_MATERIAL_SUB_CATEGORY =
      INCOMING_SAMPLES + SLASH + "material-sub-category" + SLASH + "{materialSubCategoryId}" + SLASH
          + "plant" + SLASH + "{plantCode}";
  public static final String INCOMING_SAMPLE_BY_MATERIAL_CATEGORY =
      INCOMING_SAMPLE + SLASH + "material-category" + SLASH + "{materialCategoryName}";
  public static final String INCOMING_SAMPLE_BY_PLANT = INCOMING_SAMPLE + PLANT_WISE;
  public static final String INCOMING_SAMPLES_BY_PLANT_CODE =
      INCOMING_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Mix design apis
   * 
   */
  public static final String MIX_DESIGN = BASE_API_PATH + "mix-design";
  public static final String MIX_DESIGNS = BASE_API_PATH + "mix-designs";
  public static final String MIX_DESIGN_BY_CODE = MIX_DESIGN + CODE;
  public static final String MIX_DESIGN_SEARCH = MIX_DESIGN + SEARCH;
  public static final String GET_MIX_DESIGN_BY_PLANT =
      MIX_DESIGN + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String MIX_DESIGN_BY_PLANT = MIX_DESIGN + PLANT_WISE;
  public static final String GET_MIX_DESIGN_BY_STATUS =
      MIX_DESIGN + SLASH + "status" + SLASH + "{status}";
  public static final String GET_MIX_DESIGN_BY_MATERIAL_CATEGORY =
      MIX_DESIGN + SLASH + "material-category" + SLASH + "{materialCategoryId}";
  public static final String GET_MIX_DESIGN_BY_RAW_MATERIAL =
      MIX_DESIGN + SLASH + "raw-material" + SLASH + "{rawMaterialId}";

  /*
   * Test Parameter APIs
   * 
   */
  public static final String TEST_PARAMETER = BASE_API_PATH + "test-parameter";
  public static final String TEST_PARAMETERS = BASE_API_PATH + "test-parameters";
  public static final String TEST_PARAMETER_BY_ID = TEST_PARAMETER + ID;
  public static final String GET_TEST_AND_QUALIY_PARAMETER_BY_TEST_CONFIGURE_ID =
      TEST_PARAMETER + "/test-quality-parameters" + "/{testConfigureId}" + "/incoming-sample"
          + "/{incomingSampleCode}";
  public static final String SEARCH_TEST_PARAMETER = TEST_PARAMETER + SEARCH;
  public static final String TEST_PARAMETERS_BY_TEST_CONFIGURE_ID =
      TEST_PARAMETER + "/test-parameters" + "/{testConfigureId}";
  public static final String QUALITY_PARAMETERS_BY_TEST_CONFIGURE_ID =
      TEST_PARAMETER + "/quality-parameters" + "/{testConfigureId}";
  public static final String TEST_PARAMETER_BY_TEST_CONFIGURE =
      TEST_PARAMETER + SLASH + "test-configure" + SLASH + "{testConfigureId}";

  /*
   * ProcessSample APIs
   */
  public static final String PROCESS_SAMPLE = BASE_API_PATH + "process-sample";
  public static final String PROCESS_SAMPLES = BASE_API_PATH + "process-samples";
  public static final String PROCESS_SAMPLE_BY_CODE = PROCESS_SAMPLE + CODE;
  public static final String PROCESS_SAMPLE_SEARCH = PROCESS_SAMPLE + SEARCH;
  public static final String PROCESS_SAMPLES_BY_PLANT_CODE =
      PROCESS_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String PROCESS_SAMPLE_BY_PLANT = PROCESS_SAMPLE + PLANT_WISE;

  /*
   * Mix design proportion apis
   * 
   */
  public static final String MIX_DESIGN_PROPORTION = BASE_API_PATH + "mix-design-proportion";
  public static final String MIX_DESIGN_PROPORTIONS = BASE_API_PATH + "mix-design-proportions";
  public static final String MIX_DESIGN_PROPORTION_BY_ID = MIX_DESIGN_PROPORTION + ID;
  public static final String MIX_DESIGN_PROPORTION_BY_MIX_DESIGN_CODE =
      MIX_DESIGN_PROPORTION + "/mix-design" + "/{mixDesignCode}";
  public static final String MIX_DESIGN_PROPORTION_SEARCH = MIX_DESIGN_PROPORTION + SEARCH;

  /* Parameter Result */
  public static final String PARAMETER_RESULT = BASE_API_PATH + "parameter-result";
  public static final String PARAMETER_RESULTS = BASE_API_PATH + "parameter-results";
  public static final String GET_PARAMETER_RESULT_BY_ID = PARAMETER_RESULT + ID;
  public static final String PARAMETER_RESULT_BY_ID = PARAMETER_RESULT + ID;
  public static final String PARAMETER_RESULT_BY_MATERIAL_TEST_TRIAL_CODE =
      PARAMETER_RESULT + "/matrial-test-trial" + "/{materialTestTrialCode}";
  public static final String GET_PARAMETER_RESULT_BY_PLANT =
      PARAMETER_RESULT + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String GET_PARAMETER_RESULTS_BY_MATERIAL_TEST_TRIAL_CODE_AND_MATERIAL_TEST_CODE =
      PARAMETER_RESULT + SLASH + "getParameterResultWithConfigValue" + SLASH
          + "{materialTestTrialCode}" + SLASH + "{materialTestCode}";
  public static final String PARAMETER_RESULT_BY_MATERIAL_TEST_CODE =
      PARAMETER_RESULT + SLASH + "matrial-test" + SLASH + "{materialTestCode}";
  public static final String SIEVETEST_PARAMETER_RESULT_BY_MATERIAL_TEST_CODE = PARAMETER_RESULT
      + SLASH + "matrial-test" + SLASH + "sieve-test" + SLASH + "{materialTestCode}";
  public static final String PARAMETER_RESULT_VIEW_BY_MATERIAL_TEST_CODE =
      PARAMETER_RESULT + SLASH + "result-view" + SLASH + "{materialTestCode}";

  /*
   * Material Tests
   */
  public static final String MATERIAL_TEST = BASE_API_PATH + "material-test";
  public static final String MATERIAL_TESTS = BASE_API_PATH + "material-tests";
  public static final String MATERIAL_TESTS_BY_CODE = MATERIAL_TEST + CODE;
  public static final String TEST_CONFIGURE_ID = "/{testConfigureId}";
  public static final String STATUS = "/{status}";
  public static final String MATERIAL_TEST_BY_TEST_CONFIGURE =
      MATERIAL_TESTS + "/test-configure" + TEST_CONFIGURE_ID;
  public static final String MATERIAL_TEST_BY_STATUS = MATERIAL_TESTS + "/status" + STATUS;
  public static final String MATERIAL_TEST_BY_INCOMING_SAMPLE_CODE =
      MATERIAL_TEST + SLASH + "incoming-sample" + SLASH + "{incomingSampleCode}" + PLANT_WISE;
  public static final String MATERIAL_TEST_BY_INCOMING_SAMPLE_CODE_AND_TEST =
      MATERIAL_TEST + SLASH + "incoming-sample" + SLASH + "{incomingSampleCode}" + SLASH + "test"
          + SLASH + "{testConfigId}" + PLANT_WISE;
  public static final String MATERIAL_TEST_BY_PLANT_CODE =
      MATERIAL_TEST + SLASH + "incoming-sample" + PLANT_WISE;
  public static final String SEARCH_MATERIAL_TEST = MATERIAL_TEST + SEARCH;
  public static final String GET_MATERIAL_TEST_BY_PLANT =
      MATERIAL_TEST + SLASH + "plant" + SLASH + "{plantCode}";

  public static final String MATERIAL_TEST_COMMENT = MATERIAL_TEST + SLASH + "comment";

  public static final String MATERIAL_TEST_BY_PLANT = MATERIAL_TEST + PLANT_WISE;
  public static final String MATERIAL_TESTS_BY_TESTCONFIGURE_TESTTYPE =
      MATERIAL_TESTS + "/test-configure" + "/testType" + SLASH + "{testType}";

  /*
   * material-test-trial
   * 
   */
  public static final String MATERIAL_TEST_TRIAL = BASE_API_PATH + "material-test-trial";
  public static final String MATERIAL_TEST_TRIALS = BASE_API_PATH + "material-test-trials";
  public static final String MATERIAL_TEST_TRIAL_BY_CODE = MATERIAL_TEST_TRIAL + CODE;
  public static final String MATERIAL_TEST_TRIAL_BY_MATERIAL_TEST_CODE =
      MATERIAL_TEST_TRIAL + "/material-test" + "/{materialTestCode}";
  public static final String MATERIAL_RESULT_BY_MATERIAL_TEST_CODE =
      MATERIAL_TEST_TRIAL + "/material-test" + "/average" + "/{materialTestCode}";
  public static final String GET_MATERIAL_TEST_TRIAL_BY_PLANT =
      MATERIAL_TEST_TRIAL + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String GET_MATERIAL_TEST_TRIAL_BY_TEST_CONFIGURE = MATERIAL_TEST_TRIAL + SLASH
      + "test-configure" + SLASH + "{testConfigureId}" + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String MATERIAL_TEST_TRIAL_BY_PLANT = MATERIAL_TEST_TRIAL + PLANT_WISE;


  /*
   * Test
   */
  public static final String TEST = BASE_API_PATH + "test";
  public static final String TESTS = BASE_API_PATH + "tests";
  public static final String TEST_BY_ID = TEST + ID;

  /*
   * FinishProductSample
   */
  public static final String FINISH_PRODUCT_SAMPLE = BASE_API_PATH + "finish-product-sample";
  public static final String FINISH_PRODUCT_SAMPLES = BASE_API_PATH + "finish-product-samples";
  public static final String FINISH_PRODUCT_SAMPLE_BY_ID = FINISH_PRODUCT_SAMPLE + SLASH + "{code}";
  public static final String FINISH_PRODUCT_SAMPLE_BY_MIX_DESIGN_CODE =
      FINISH_PRODUCT_SAMPLE + SLASH + "mix-design-code" + SLASH + "{mixDesignCode}";
  public static final String FINISH_PRODUCT_SAMPLE_BY_CONCRETE_MIXER_ID =
      FINISH_PRODUCT_SAMPLE + SLASH + "concrete-mixer-id" + SLASH + "{concreteMixerId}";
  public static final String FINISH_PRODUCT_SAMPLE_SEARCH = FINISH_PRODUCT_SAMPLE + SEARCH;
  public static final String FINISH_PRODUCT_SAMPLES_BY_PLANT_CODE =
      FINISH_PRODUCT_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String FINISH_PRODUCT_SAMPLE_BY_STATUS =
      FINISH_PRODUCT_SAMPLE + SLASH + "status" + SLASH + "{status}";
  public static final String GET_FINISH_PRODUCT_BY_MATERIAL_CATEGORY =
      FINISH_PRODUCT_SAMPLES + SLASH + "material-category" + SLASH + "{materialCategoryId}";

  /*
   * finish product sample issue apis
   * 
   */
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE =
      BASE_API_PATH + "finish-product-sample-issue";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES =
      BASE_API_PATH + "finish-product-sample-issues";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_BY_ID = FINISH_PRODUCT_SAMPLE_ISSUE + CODE;
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_SEARCH =
      FINISH_PRODUCT_SAMPLE_ISSUE + SEARCH;
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT_CODE =
      FINISH_PRODUCT_SAMPLE_ISSUES + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT =
      FINISH_PRODUCT_SAMPLE_ISSUE + PLANT_WISE;
  public static final String FINISH_PRODUCT_SAMPLE_BY_PLANT = FINISH_PRODUCT_SAMPLE + PLANT_WISE;
  /*
   * incoming samples count APIs
   */
  public static final String INCOMING_SAMPLES_COUNT = BASE_API_PATH + "incoming-samples-count";
  public static final String INCOMING_SAMPLES_COUNT_BY_MATERIAL =
      BASE_API_PATH + "incoming-samples-count-by-material";
  public static final String MATERIAL_SAMPLE_COUNT_BY_MATERIAL_CATEGORY =
      INCOMING_SAMPLES_COUNT_BY_MATERIAL + SLASH + "{materialCategoryName}" + SLASH + "{plantCode}";

  public static final String SUB_CATEGORY = "materialSubCategory";
  public static final String MATERIAL_SAMPLE_COUNT_BY_MATERIAL_SUB_CATEGORY =
      INCOMING_SAMPLES_COUNT_BY_MATERIAL + SLASH + SUB_CATEGORY + SLASH
          + "{materialSubCategoryName}" + SLASH + "{plantCode}";
  public static final String MATERIAL_SUB_CATEGORY_STATUS_COUNT = INCOMING_SAMPLES_COUNT + SLASH
      + "material-sub-category" + SLASH + "{materialSubCategoryName}" + SLASH + "{plantCode}";
  public static final String MATERIAL_CATEGORY_STATUS_COUNT = INCOMING_SAMPLES_COUNT + SLASH
      + "material-category" + SLASH + "{materialCategoryName}" + SLASH + "{plantCode}";

  /*
   * qualityParameter
   */
  public static final String QUALITY_PARAMETER = BASE_API_PATH + "quality-parameter";
  public static final String QUALITY_PARAMETER_BY_ID = QUALITY_PARAMETER + ID;
  public static final String QUALITY_PARAMETERS = BASE_API_PATH + "quality-parameters";
  public static final String MATERIAL_SUB_CATEGORY_ID = "/{materialSubCategoryId}";
  public static final String GET_QUALITY_PARAMETERS_BY_MATERIAL_SUB_CATEGORY_ID =
      QUALITY_PARAMETER + "/qualityparameter" + MATERIAL_SUB_CATEGORY_ID;

  /*
   * MaterialAcceptedValue Apis
   */
  public static final String MATERIAL_ACCEPTED_VALUE = BASE_API_PATH + "material-accepted-value";
  public static final String MATERIAL_ACCEPTED_VALUES = BASE_API_PATH + "material-accepted-values";
  public static final String MATERIAL_ACCEPTED_VALUE_BY_ID = MATERIAL_ACCEPTED_VALUE + ID;
  public static final String MATERIAL_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID =
      MATERIAL_ACCEPTED_VALUE + "/dto" + "/test-configure" + "/{testConfigureId}";
  public static final String SEARCH_MATERIAL_ACCEPTED_VALUE = MATERIAL_ACCEPTED_VALUE + SEARCH;
  public static final String MATERIAL_ACCEPTED_VALUE_DTO_BY_TEST_CONFIGURE_ID =
      MATERIAL_ACCEPTED_VALUE + "/test-configure" + "/{testConfigureId}";
  /*
   * AcceptedValue
   */
  public static final String ACCEPTED_VALUE = BASE_API_PATH + "accepted-value";
  public static final String ACCEPTED_VALUES = BASE_API_PATH + "accepted-values";
  public static final String ACCEPTED_VALUE_BY_ID = ACCEPTED_VALUE + ID;
  public static final String GET_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID =
      ACCEPTED_VALUE + SLASH + "dto" + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  public static final String SEARCH_ACCEPTED_VALUE = ACCEPTED_VALUE + SEARCH;
  public static final String GET_ACCEPTED_VALUE_DTO_BY_TEST_CONFIGURE_ID =
      ACCEPTED_VALUE + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  /*
   * test Report
   */
  public static final String TEST_REPORT = BASE_API_PATH + "test-report";
  public static final String TEST_REPORT_DETAIL = BASE_API_PATH + "test-detail-report";
  public static final String MATERIAL_TEST_CODE = "material-test" + SLASH + "{materialTestCode}";
  public static final String MATERIAL_TEST_REPORT = TEST_REPORT + SLASH + MATERIAL_TEST_CODE;
  public static final String INCOMING_CODE = "incoming-sample" + SLASH + "{icomingSampleCode}";
  public static final String MATERIAL_TEST_DETAIL_REPORT = TEST_REPORT + SLASH + INCOMING_CODE;
  public static final String MATERIAL_TEST_REPORT_DETAIL =
      TEST_REPORT_DETAIL + SLASH + MATERIAL_TEST_CODE + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * test Report
   */
  public static final String SIEVE_TEST_CODE = "sieve-test" + SLASH + "{sieveTestCode}";
  public static final String SIEVE_TEST_REPORT = TEST_REPORT + SLASH + SIEVE_TEST_CODE;
  public static final String CONCRETE_STRENGTH = BASE_API_PATH + SLASH + "concrete-type" + SLASH
      + "{concreteTestType}" + SLASH + "concrete-name" + SLASH + "{concreteTestName}";
  public static final String FINISH_PRODUCT_TEST_DETAIL_REPORT =
      TEST_REPORT + SLASH + "finish-product-test" + SLASH + "{finishProductTestCode}";
  /*
   * Finish Product Sample
   */
  public static final String FINISH_PRODUCT_SAMPLE_ID =
      "finish-product" + SLASH + "{finishProductSampleId}";
  public static final String FINISH_PRODUCT_RESULT_REPORT =
      TEST_REPORT + SLASH + FINISH_PRODUCT_SAMPLE_ID;
  public static final String FINISH_PRODUCT_RESULTS = "all-results";
  public static final String FINISH_PRODUCT_ALL_RESULTS_REPORT =
      TEST_REPORT + SLASH + FINISH_PRODUCT_RESULTS;
  public static final String INCOMING_SAMPLE_SUMMARY_REPORT =
      BASE_API_PATH + "incoming-sample-summary-report" + SLASH + "{incomingSampleCode}" + SLASH
          + "plant" + SLASH + "{plantCode}";
  public static final String INCOMING_SAMPLE_DELIVERY_REPORT =
      BASE_API_PATH + "incoming-sample-delivery-report" + SLASH + "{incomingSampleCode}" + SLASH
          + "{reportFormat}" + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String CONCRETE_TEST_REPORT = BASE_API_PATH + "concrete-test-report" + SLASH
      + "{finishProductTestCode}" + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String MATERIAL_TEST_TRIALS_WISE_BY_MATERIAL_TEST_CODE =
      BASE_API_PATH + "material-test-trials-wise" + SLASH + "{materialTestCode}";
  public static final String CONCRETE_STRENGTHS_BY_PLANT =
      BASE_API_PATH + "concrete-strengths" + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String SIEVE_TRIALS_BY_MATERIAL_TEST_CODE_PLANT_CODE =
      BASE_API_PATH + "material-test-sieve" + SLASH + "{materialTestCode}" + SLASH + "plant" + SLASH
          + "{plantCode}";
  public static final String SIEVE_TEST_GRAPH_BY_MATERIAL_TEST_CODE =
      BASE_API_PATH + "material-test-graph" + SLASH + "{materialTestCode}";
  /*
   * File Export
   */
  public static final String EXPORT_MIXDESIGN = BASE_API_PATH + SLASH + "CSV" + SLASH + "download";
  public static final String UPLOAD_MIXDESIGN = BASE_API_PATH + SLASH + "CSV" + SLASH + "upload";
  public static final String MAIL_REPORT = BASE_API_PATH + "mail-report";

  /*
   * Parameter Equation apis
   */
  public static final String PARAMETER_EQUATION = BASE_API_PATH + "parameter-equation";
  public static final String PARAMETER_EQUATION_BY_ID = PARAMETER_EQUATION + ID;
  public static final String DELETE_PARAMETER_EQUATION = PARAMETER_EQUATION + ID;
  public static final String PARAMETER_EQUATIONS = BASE_API_PATH + "parameter-equations";
  public static final String PARAMETER_EQUATION_BY_TEST_PARAMETER_ID =
      PARAMETER_EQUATION + SLASH + "test-parameter" + SLASH + "{testParameterId}";
  public static final String PARAMETER_EQUATION_BY_TEST_CONFIGURE_ID =
      PARAMETER_EQUATION + SLASH + "test-configure" + SLASH + "{testConfigureId}";

  /*
   * Parameter Equation Element apis
   */
  public static final String PARAMETER_EQUATION_ELEMENT =
      BASE_API_PATH + "parameter-equation-element";
  public static final String PARAMETER_EQUATION_ELEMENT_BY_ID = PARAMETER_EQUATION_ELEMENT + ID;
  public static final String DELETE_PARAMETER_EQUATION_ELEMENT = PARAMETER_EQUATION_ELEMENT + ID;
  public static final String PARAMETER_EQUATION_ELEMENTS =
      BASE_API_PATH + "parameter-equation-elements";
  public static final String PARAMETER_EQUATION_ELEMENT_BY_TEST_PARAMETER_ID =
      PARAMETER_EQUATION_ELEMENT + SLASH + "test-parameter" + SLASH + "{testParameterId}";
  public static final String PARAMETER_EQUATION_ELEMENT_BY_PARAMETER_EQUATION_ID =
      PARAMETER_EQUATION_ELEMENT + SLASH + "parameter-equation" + SLASH + "{parameterEquationId}";
  public static final String PARAMETER_EQUATION_ELEMENT_BY_TEST_PARAMETER =
      PARAMETER_EQUATION_ELEMENT + SLASH + "parameter-equation" + SLASH + "test-parameter" + SLASH
          + "{testParameterId}";
  /*
   * Finish Product Trial apis
   */
  public static final String FINISH_PRODUCT_TRIAL = BASE_API_PATH + "finish-product-trial";
  public static final String FINISH_PRODUCT_TRIALS = BASE_API_PATH + "finish-product-trials";
  public static final String FINISH_PRODUCT_TRIAL_BY_ID = FINISH_PRODUCT_TRIAL + ID;
  public static final String FINISH_PRODUCT_TRIALS_BY_FINISH_PRODUCT_TEST_CODE =
      FINISH_PRODUCT_TRIALS + SLASH + "finish-product-test" + SLASH + "{finishProductTestCode}";
  public static final String FINISH_PRODUCT_TEST_STATUS_BY_FINISH_PRODUCT_TEST_CODE =
      FINISH_PRODUCT_TRIAL + SLASH + "finish-product-test-status" + SLASH
          + "{finishProductTestCode}";
  public static final String FINISH_PRODUCT_TRIAL_BY_PLANT = FINISH_PRODUCT_TRIAL + PLANT_WISE;
  public static final String FINISH_PRODUCT_PARAMETER_RESULTS =
      BASE_API_PATH + "finish-product-parameter-results";
  public static final String FINISH_PRODUCT_PARAMETER_RESULT =
      BASE_API_PATH + "finish-product-parameter-result";
  public static final String FINISH_PRODUCT_PARAMETER_RESULT_BY_FINISH_PRODUCT_TEST_CODE =
      FINISH_PRODUCT_PARAMETER_RESULT + SLASH + "finish-product-test-result" + SLASH
          + "{finishProductTestCode}";
  public static final String FINISH_PRODUCT_RESULT_BY_FINISH_PRODUCT_CODE =
      FINISH_PRODUCT_TRIAL + SLASH + "finish-product-test" + SLASH + "{finishProductCode}";
  /*
   * Finish Product Test
   */
  public static final String FINISH_PRODUCT_TEST = BASE_API_PATH + "finish-product-test";
  public static final String FINISH_PRODUCT_TESTS = BASE_API_PATH + "finish-product-tests";
  public static final String FINISH_PRODUCT_TEST_BY_CODE = FINISH_PRODUCT_TEST + CODE;
  public static final String GET_FINISH_PRODUCT_TESTS_BY_TESTCONFIGURE =
      FINISH_PRODUCT_TESTS + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  public static final String GET_FINISH_PRODUCT_TESTS_BY_FINISH_PRODUCT_SAMPLE_TESTCONFIGURE =
      FINISH_PRODUCT_TESTS + SLASH + "finish-product-sample" + SLASH + "{finishProductSampleCode}"
          + SLASH + "test-configure" + SLASH + "{testConfigureId}";

  public static final String FINISH_PRODUCT_TEST_BY_PLANT = FINISH_PRODUCT_TEST + PLANT_WISE;
  public static final String FINISH_PRODUCT_TEST_COMMENT = FINISH_PRODUCT_TEST + SLASH + "comment";
  public static final String GET_FINISH_PRODUCT_TESTS_BY_FINISH_PRODUCT_SAMPLE =
      FINISH_PRODUCT_TEST + SLASH + "finish-product-sample" + SLASH + "{finishProductSampleCode}";

  /*
   * Employee apis
   */
  public static final String EMAIL_RECIPIENT = BASE_API_PATH + "email-recipient";
  public static final String EMAIL_RECIPIENTS = BASE_API_PATH + "email-recipients";
  public static final String EMAIL_RECIPIENTS_BY_RECIPIENT_TYPE = EMAIL_RECIPIENT + SLASH
      + "{emailGroupId}" + SLASH + "recipientType" + SLASH + "{recipientType}";
  public static final String EMAIL_RECIPIENT_BY_ID = EMAIL_RECIPIENT + ID;

  public static final String EMAIL_GROUPS = BASE_API_PATH + "email-groups";
  public static final String EMAIL_GROUP = BASE_API_PATH + "email-group";
  public static final String EMAIL_GROUP_BY_ID = EMAIL_GROUP + ID;
  public static final String EMAIL_GROUP_BY_SHEDULE =
      EMAIL_GROUPS + SLASH + "email-group-schedule" + SLASH + "{schedule}";
  public static final String EMAIL_GROUP_BY_PLANT_CODE =
      EMAIL_GROUPS + SLASH + "email-group-plant-code" + SLASH + "{plantCode}";
  public static final String EMAIL_GROUP_BY_PLANT_CODE_AND_STATUS = EMAIL_GROUPS + SLASH
      + "plant-code" + SLASH + "{plantCode}" + SLASH + "status" + SLASH + "{status}";
  public static final String EMAIL_GROUP_BY_PLANT_CODE_AND_STATUS_SCEHDULE =
      EMAIL_GROUPS + SLASH + "plant-code" + SLASH + "{plantCode}" + SLASH + "status" + SLASH
          + "{status}" + SLASH + "schedule" + SLASH + "{schedule}";
  public static final String EMAIL_GROUP_BY_PLANT_CODE_ADMIN_STATUS =
      EMAIL_GROUPS + SLASH + "email-group-admin-status" + SLASH + "plant-code" + SLASH
          + "{plantCode}" + SLASH + "admin-status" + SLASH + "{adminStatus}";
  public static final String EMAIL_GROUP_BY_ADMIN_STATUS =
      EMAIL_GROUPS + SLASH + "email-group-admin-status" + SLASH + "{adminStatus}";
  public static final String EMAIL_GROUP_EDIT_NAME = EMAIL_GROUP + SLASH + "name";

  /*
   * email group notification days APIs
   */
  public static final String EMAIL_NOTIFICATION = BASE_API_PATH + "email-notification-day";
  public static final String EMAIL_NOTIFICATIONS = BASE_API_PATH + "email-notification-days";
  public static final String EMAIL_NOTIFICATIONS_BY_GROUP = EMAIL_NOTIFICATION + SLASH + "group";
  public static final String EMAIL_NOTIFICATION_BY_ID = EMAIL_NOTIFICATION + ID;
  public static final String EMAIL_NOTIFICATIONS_BY_PLANT_CODE =
      EMAIL_NOTIFICATION + SLASH + "{plantCode}";
  /*
   * Test Equation
   */
  public static final String TEST_EQUATION = BASE_API_PATH + "test-equation";
  public static final String TEST_EQUATIONS = BASE_API_PATH + "test-equations";
  public static final String TEST_EQUATION_BY_ID = TEST_EQUATION + ID;
  public static final String TEST_EQUATION_BY_TEST_CONFIGURE_ID =
      TEST_EQUATION + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  public static final String TEST_EQUATION_BY_EQUATION_ID =
      TEST_EQUATION + SLASH + "equation" + SLASH + "{equationId}";
  public static final String TEST_EQUATION_BY_TEST_PARAMETER_ID =
      TEST_EQUATION + SLASH + "test-parameter" + SLASH + "{testParameterId}";
  /*
   * Test Equation Parameter
   */
  public static final String TEST_EQUATION_PARAMETER = BASE_API_PATH + "test-equation-parameter";
  public static final String TEST_EQUATION_PARAMETERS = BASE_API_PATH + "test-equation-parameters";
  public static final String TEST_EQUATION_PARAMETER_BY_ID = TEST_EQUATION_PARAMETER + ID;
  public static final String TEST_EQUATION_PARAMETER_BY_TEST_PARAMETER_ID =
      TEST_EQUATION_PARAMETER + SLASH + "test-parameter" + SLASH + "{testParameterId}";
  public static final String TEST_EQUATION_PARAMETER_BY_TEST_EQUATION_ID =
      TEST_EQUATION_PARAMETER + SLASH + "test-equation" + SLASH + "{testEquationId}";
  /*
   * Email Points
   */
  public static final String EMAIL_POINT = BASE_API_PATH + "email-point";
  public static final String EMAIL_POINTS = BASE_API_PATH + "email-points";
  public static final String EMAIL_POINTS_BY_STATUS = EMAIL_POINT + SLASH + "{status}";
  public static final String EMAIL_POINTS_BY_ADMIN_STATUS =
      EMAIL_POINT + SLASH + "admin-status" + SLASH + "{status}";

  /*
   * Finish Product Accepted Value
   */
  public static final String FINISH_PRODUCT_ACCEPTED_VALUE =
      BASE_API_PATH + "finish-product-accepted-value";
  public static final String FINISH_PRODUCT_ACCEPTED_VALUES =
      BASE_API_PATH + "finish-product-accepted-values";
  public static final String FINISH_PRODUCT_ACCEPTED_VALUE_BY_ID =
      FINISH_PRODUCT_ACCEPTED_VALUE + ID;
  public static final String GET_FINISH_PRODUCT_ACCEPTED_VALUE_BY_TEST_PARAMETER =
      FINISH_PRODUCT_ACCEPTED_VALUE + SLASH + "test-parameter" + SLASH + "{testParameterId}";

  public static final String GET_FINISH_PRODUCT_ACCEPTED_VALUE_BY_TEST_CONFIGURE =
      FINISH_PRODUCT_ACCEPTED_VALUE + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  /*
   * Export & Import
   */
  public static final String EXPORT_CUSTOMER =
      BASE_API_PATH + SLASH + "customer" + SLASH + "download";
  public static final String IMPORT_CUSTOMER =
      BASE_API_PATH + SLASH + "customer" + SLASH + "upload";
  public static final String EXPORT_SUPPLIER =
      BASE_API_PATH + SLASH + "supplier" + SLASH + "download";
  public static final String IMPORT_SUPPLIER =
      BASE_API_PATH + SLASH + "supplier" + SLASH + "upload";
  public static final String EXPORT_EMPLOYEE =
      BASE_API_PATH + SLASH + "employee" + SLASH + "download";
  public static final String IMPORT_EMPLOYEE =
      BASE_API_PATH + SLASH + "employee" + SLASH + "upload";
  public static final String EXPORT_PROJECT =
      BASE_API_PATH + SLASH + "project" + SLASH + "download";
  public static final String IMPORT_PROJECT = BASE_API_PATH + SLASH + "project" + SLASH + "upload";
  public static final String EXPORT_PLANT_EQUIPMENT =
      PLANT_EQUIPMENT + SLASH + "CSV" + SLASH + "download";
  public static final String UPLOAD_PLANT_EQUIPMENT =
      PLANT_EQUIPMENT + SLASH + "CSV" + SLASH + "upload";

  private EndpointURI() {}
}
