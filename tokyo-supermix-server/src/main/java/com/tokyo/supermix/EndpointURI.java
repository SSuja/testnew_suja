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
  public static final String DELETE_SUPPLIER_CATEGORY = SUPPLIER_CATEGORY + ID;
  public static final String SUPPLIER_CATEGORIES = BASE_API_PATH + "supplier-categories";

  /*
   * Supplier Apis
   */
  public static final String SUPPLIER = BASE_API_PATH + "supplier";
  public static final String SUPPLIERS = BASE_API_PATH + "suppliers";
  public static final String GET_SUPPLIER_BY_ID = SUPPLIER + ID;
  public static final String DELETE_SUPPLIER = SUPPLIER + ID;
  public static final String GET_SUPPLIER_BY_SUPPLIER_CATEGORY_ID =
      SUPPLIER + SLASH + "supplier-category" + SLASH + "{suppilerCategoryId}";
  public static final String SUPPLIER_SEARCH = SUPPLIER + SEARCH;
  public static final String GET_SUPPLIERS_BY_PLANT_CODE =
      SUPPLIERS + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Employee apis
   */
  public static final String EMPLOYEE = BASE_API_PATH + "employee";
  public static final String EMPLOYEE_BY_ID = EMPLOYEE + ID;
  public static final String EMPLOYEES = BASE_API_PATH + "employees";
  public static final String SEARCH_EMPLOYEE = EMPLOYEE + SEARCH;
  public static final String GET_EMPLOYEES_BY_PLANT_CODE =
      EMPLOYEES + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Material Category APIs
   */
  public static final String MATERIAL_CATEGORY = BASE_API_PATH + "material-category";
  public static final String MATERIAL_CATEGORIES = BASE_API_PATH + "material-categories";
  public static final String GET_MATERIAL_CATEGORY_BY_ID = MATERIAL_CATEGORY + ID;
  public static final String DELETE_MATERIAL_CATEGORY = MATERIAL_CATEGORY + ID;
  /*
   * Pour APIs
   */
  public static final String POUR = BASE_API_PATH + "pour";
  public static final String POURS = BASE_API_PATH + "pours";
  public static final String GET_POUR_BY_ID = POUR + ID;
  public static final String DELETE_POUR = POUR + ID;
  public static final String POUR_SEARCH = POUR + SEARCH;
  public static final String GET_POURS_BY_PLANT_CODE =
      POURS + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Unit apis
   */
  public static final String UNIT = BASE_API_PATH + "unit";
  public static final String UNITS = BASE_API_PATH + "units";
  public static final String GET_UNIT_BY_ID = UNIT + ID;
  public static final String DELETE_UNIT_BY_ID = UNIT + ID;

  /*
   * MaterialSubCategory Apis
   */
  public static final String MATERIAL_SUB_CATEGORY = BASE_API_PATH + "material-sub-category";
  public static final String MATERIAL_SUB_CATEGORIES = BASE_API_PATH + "material-sub-categories";
  public static final String MATERIAL_CATEGORY_ID = "/{materialCategoryId}";
  public static final String GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY =
      MATERIAL_SUB_CATEGORY + "/category" + MATERIAL_CATEGORY_ID;
  public static final String GET_MATERIAL_SUB_CATEGORY_BY_ID = MATERIAL_SUB_CATEGORY + ID;
  public static final String DELETE_MATERIAL_SUB_CATEGORY = MATERIAL_SUB_CATEGORY + ID;
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
  public static final String GET_TEST_CONFIGURE_BY_ID = TEST_CONFIGURE + ID;
  public static final String DELETE_TEST_CONFIGURE = TEST_CONFIGURE + ID;
  public static final String GET_TEST_CONFIGURE_BY_TEST_TYPE_ID =
      TEST_CONFIGURE + SLASH + "test-type" + SLASH + "{testTypeId}";
  public static final String GET_TEST_CONFIGURE_BY_CORE_TEST =
      TEST_CONFIGURE + SLASH + "core-test" + SLASH + "{coreTest}";
  public static final String TEST_CONFIGURE_BY_ID_AND_CORE_TEST =
      TEST_CONFIGURE + SLASH + "core-test";
  public static final String TEST_CONFIGURE_SEARCH = TEST_CONFIGURE + SEARCH;
  /*
   * Material State
   */
  public static final String MATERIAL_STATE = BASE_API_PATH + "material-state";
  public static final String MATERIAL_STATES = BASE_API_PATH + "material-states";
  public static final String GET_MATERIAL_STATE_BY_ID = MATERIAL_STATE + ID;
  public static final String DELETE_MATERIAL_STATE = MATERIAL_STATE + ID;
  /*
   * Raw Material apis
   */
  public static final String RAW_MATERIAL = BASE_API_PATH + "raw-material";
  public static final String RAW_MATERIALS = BASE_API_PATH + "raw-materials";
  public static final String GET_RAW_MATERIAL_BY_ID = RAW_MATERIAL + ID;
  public static final String DELETE_RAW_MATERIAL = RAW_MATERIAL + ID;
  public static final String SEARCH_RAW_MATERIAL = RAW_MATERIAL + SEARCH;
  /*
   * EquipmentPlantCalibration apis
   */
  public static final String EQUIPMENT_PLANT_CALIBRATION =
      BASE_API_PATH + "plant-equipment-calibration";
  public static final String EQUIPMENT_PLANT_CALIBRATIONS =
      BASE_API_PATH + "plant-equipment-calibrations";
  public static final String GET_EQUIPMENT_PLANT_CALIBRATION_BY_ID =
      EQUIPMENT_PLANT_CALIBRATION + ID;
  public static final String DELETE_EQUIPMENT_PLANT_CALIBRATION = EQUIPMENT_PLANT_CALIBRATION + ID;
  public static final String EQUIPMENT_PLANT_CALIBRATION_SEARCH =
      EQUIPMENT_PLANT_CALIBRATION + SEARCH;
  public static final String GET_EQUIPMENT_PLANT_CALIBRATIONS_BY_PLANT_CODE =
      EQUIPMENT_PLANT_CALIBRATIONS + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * PlantEquipment
   * 
   */
  public static final String PLANT_EQUIPMENT = BASE_API_PATH + "plantequipment";
  public static final String PLANT_EQUIPMENTS = BASE_API_PATH + "plantequipments";
  public static final String DELETE_PLANT_EQUIPMENT = PLANT_EQUIPMENT + SERIALNO;
  public static final String GET_PLANTEQUIPMENT_BY_SERIALNO = PLANT_EQUIPMENT + SERIALNO;
  public static final String PLANTEQUIPMENT_SEARCH = PLANT_EQUIPMENT + SEARCH;
  public static final String GET_PLANTEQUIPMENTS_BY_PLANT_CODE =
      PLANT_EQUIPMENTS + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Customer apis
   */
  public static final String CUSTOMER = BASE_API_PATH + "customer";
  public static final String CUSTOMERS = BASE_API_PATH + "customers";
  public static final String DELETE_CUSTOMER = CUSTOMER + ID;
  public static final String GET_CUSTOMER_BY_ID = CUSTOMER + ID;
  public static final String CUSTOMER_SEARCH = CUSTOMER + SEARCH;
  public static final String GET_CUSTOMERS_BY_PLANT_CODE =
      CUSTOMERS + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String GET_TEST_DETAILS_BY_CONFIGURE_ID =
      TEST_CONFIGURE + SLASH + "testDetails" + ID;
  /*
   * Equation APIs
   */
  public static final String EQUATION = BASE_API_PATH + "equation";
  public static final String EQUATIONS = BASE_API_PATH + "equations";
  public static final String EQUATION_BY_ID = EQUATION + ID;
  /*
   * Parameter apis
   * 
   */
  public static final String PARAMETER = BASE_API_PATH + "parameter";
  public static final String PARAMETERS = BASE_API_PATH + "parameters";
  public static final String GET_PARAMETER_BY_ID = PARAMETER + ID;
  public static final String DELETE_PARAMETER_BY_ID = PARAMETER + ID;
  public static final String PARAMETER_SEARCH = PARAMETER + SEARCH;

  /*
   * 
   * TestType
   */
  public static final String TEST_TYPE = BASE_API_PATH + "test-type";
  public static final String DELETE_TEST_TYPE = TEST_TYPE + ID;
  public static final String GET_TEST_TYPE_BY_ID = TEST_TYPE + ID;
  public static final String TEST_TYPES = BASE_API_PATH + "test-types";
  public static final String GET_TEST_TYPES_BY_MATERIAL_SUB_CATEGORY_ID =
      TEST_TYPE + "/materialSubCategory" + "/{materialSubCategoryId}";
  public static final String SEARCH_TEST_TYPE = TEST_TYPE + SEARCH;
  /*
   * Project APIs
   */
  public static final String PROJECT = BASE_API_PATH + "project";
  public static final String PROJECT_BY_ID = PROJECT + CODE;
  public static final String PROJECTS = BASE_API_PATH + "projects";
  public static final String SEARCH_PROJECT = PROJECT + SEARCH;
  public static final String GET_PROJECTS_BY_PLANT_CODE =
      PROJECTS + SLASH + "plant" + SLASH + "{plantCode}";
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
  public static final String INCOMING_SAMPLES_BY_PLANT_CODE =
      INCOMING_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String INCOMING_SAMPLE_BY_MATERIAL_CATEGORY =
      INCOMING_SAMPLE + SLASH + "material-category" + SLASH + "{materialCategoryName}";
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
  /*
   * ProcessSampleLoad Apis
   */
  public static final String PROCESS_SAMPLE_LOAD = BASE_API_PATH + "process-sample-load";
  public static final String PROCESS_SAMPLE_LOADS = BASE_API_PATH + "process-sample-loads";
  public static final String PROCESS_SAMPLE_LOAD_BY_ID = PROCESS_SAMPLE_LOAD + ID;
  public static final String PROCESS_SAMPLE_LOADS_BY_PLANT_CODE =
      PROCESS_SAMPLE_LOADS + SLASH + "plant" + SLASH + "{plantCode}";
 
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

  /*
   * ProcessSample APIs
   */
  public static final String PROCESS_SAMPLE = BASE_API_PATH + "process-sample";
  public static final String PROCESS_SAMPLES = BASE_API_PATH + "process-samples";
  public static final String PROCESS_SAMPLE_BY_CODE = PROCESS_SAMPLE + CODE;
  public static final String PROCESS_SAMPLE_SEARCH = PROCESS_SAMPLE + SEARCH;
  public static final String PROCESS_SAMPLES_BY_PLANT_CODE =
      PROCESS_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";

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

  /*
   * ConcreteStrengthTest APIs
   */
  public static final String CONCRETE_STRENGTH_TEST = BASE_API_PATH + "concrete-strength-test";
  public static final String CONCRETE_STRENGTH_TESTS = BASE_API_PATH + "concrete-strength-tests";
  public static final String CONCRETE_STRENGTH_TEST_BY_ID = CONCRETE_STRENGTH_TEST + ID;
  public static final String SEARCH_CONCRETE_STRENGTH_TEST = CONCRETE_STRENGTH_TEST + SEARCH;

  /*
   * Equation Parameter
   */
  public static final String EQUATION_PARAMETER = BASE_API_PATH + "equation-parameter";
  public static final String EQUATION_PARAMETER_BY_ID = EQUATION_PARAMETER + ID;
  public static final String EQUATION_PARAMETERS = BASE_API_PATH + "equation-parameters";
  public static final String EQUATION_ID = "/{equationId}";
  public static final String GET_PARAMETERS_BY_EQUATION_ID =
      EQUATION_PARAMETER + "/equationparameter" + EQUATION_ID;

  /* Parameter Result */
  public static final String PARAMETER_RESULT = BASE_API_PATH + "parameter-result";
  public static final String PARAMETER_RESULTS = BASE_API_PATH + "parameter-results";
  public static final String GET_PARAMETER_RESULT_BY_ID = PARAMETER_RESULT + ID;
  public static final String DELETE_PARAMETER_RESULT_BY_ID = PARAMETER_RESULT + ID;
  public static final String PARAMETER_RESULT_BY_MATERIAL_TEST_TRIAL_CODE =
      PARAMETER_RESULT + "/matrial-test-trial" + "/{materialTestTrialCode}";
  public static final String GET_PARAMETER_RESULT_BY_PLANT =
      PARAMETER_RESULT + SLASH + "plant" + SLASH + "{plantCode}";
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
      MATERIAL_TEST + SLASH + "incoming-sample" + SLASH + "{incomingSampleCode}";
  public static final String SEARCH_MATERIAL_TEST = MATERIAL_TEST + SEARCH;
  public static final String GET_MATERIAL_TEST_BY_PLANT =
      MATERIAL_TEST + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * material-test-trial
   * 
   */
  public static final String MATERIAL_TEST_TRIAL = BASE_API_PATH + "material-test-trial";
  public static final String MATERIAL_TEST_TRIALS = BASE_API_PATH + "material-test-trials";
  public static final String MATERIAL_TEST_TRIAL_BY_CODE = MATERIAL_TEST_TRIAL + CODE;
  public static final String MATERIAL_TEST_TRIAL_BY_MATERIAL_TEST_CODE =
      MATERIAL_TEST_TRIAL + "/material-test" + "/{materialTestCode}";
  public static final String AVERAGE_BY_MATERIAL_TEST_CODE =
      MATERIAL_TEST_TRIAL + "/material-test" + "/average" + "/{materialTestCode}";
  public static final String GET_MATERIAL_TEST_TRIAL_BY_PLANT =
      MATERIAL_TEST_TRIAL + SLASH + "plant" + SLASH + "{plantCode}";

  /*
   * sieve-test-trial
   * 
   */
  public static final String SIEVE_TEST_TRIAL = BASE_API_PATH + "sieve-test-trial";
  public static final String SIEVE_TEST_TRIALS = BASE_API_PATH + "sieve-test-trials";
  public static final String SIEVE_TEST_TRIAL_BY_ID = SIEVE_TEST_TRIAL + ID;
  public static final String SIEVE_TEST_TRIAL_BY_SIEVE_TEST_CODE =
      SIEVE_TEST_TRIAL + SLASH + "sieve-test" + SLASH + "{sieveTestCode}";
  public static final String GET_SIEVE_TEST_TRIAL_BY_PLANT =
      SIEVE_TEST_TRIAL + SLASH + "plant" + SLASH + "{plantCode}";

  /*
   * Sieve Size
   */
  public static final String SIEVE_SIZE = BASE_API_PATH + "sieve-size";
  public static final String SIEVE_SIZES = BASE_API_PATH + "sieve-sizes";
  public static final String SIEVE_SIZE_BY_ID = SIEVE_SIZE + ID;
  public static final String SIEVE_SIZE_BY_MATERIAL_SUB_CATEGORY_ID =
      SIEVE_SIZE + SLASH + "material-sub-category" + SLASH + "{materialSubCategoryId}";
  public static final String SIEVE_SIZE_ACCEPTED_VALUE_BY_MATERIAL_SUB_CATEGORY_ID =
      SIEVE_SIZE + SLASH + "sieve-accepted-value" + SLASH + "{materialSubCategoryId}";
  public static final String SIEVE_SIZE_SEARCH = SIEVE_SIZE + SEARCH;
  /*
   * Sieve Accepted Value
   */
  public static final String SIEVE_ACCEPTED_VALUE = BASE_API_PATH + "sieve-accepted-value";
  public static final String SIEVE_ACCEPTED_VALUES = BASE_API_PATH + "sieve-accepted-values";
  public static final String SIEVE_ACCEPTED_VALUE_BY_ID = SIEVE_ACCEPTED_VALUE + ID;
  public static final String SIEVE_ACCEPTED_VALUE_SEARCH = SIEVE_ACCEPTED_VALUE + SEARCH;
  /*
   * SieveTest
   * 
   */
  public static final String SIEVE_TEST = BASE_API_PATH + "sieve-test";
  public static final String SIEVE_TESTS = BASE_API_PATH + "sieve-tests";
  public static final String SIEVE_TEST_BY_CODE = SIEVE_TEST + CODE;
  public static final String SEARCH_SIEVE_TEST = SIEVE_TEST + SEARCH;
  /*
   * Test
   */
  public static final String TEST = BASE_API_PATH + "test";
  public static final String TESTS = BASE_API_PATH + "tests";
  public static final String GET_TEST_BY_ID = TEST + ID;
  public static final String DELETE_TEST = TEST + ID;

  /*
   * FinenessModulus
   * 
   */
  public static final String FINENESS_MODULUS = BASE_API_PATH + "fineness-modulus";
  public static final String FINENESS_MODULUS_BY_ID = FINENESS_MODULUS + ID;
  public static final String FINENESS_MODULUS_BY_MATERIALSUBCATEGORY =
      FINENESS_MODULUS + "/materialSubCategory" + "/{materialSubCategoryId}";
  public static final String SEARCH_FINENESS_MODULUS = FINENESS_MODULUS + SEARCH;

  /*
   * FinishProductSample
   */
  public static final String FINISH_PRODUCT_SAMPLE = BASE_API_PATH + "finish-product-sample";
  public static final String FINISH_PRODUCT_SAMPLES = BASE_API_PATH + "finish-product-samples";
  public static final String FINISH_PRODUCT_SAMPLE_BY_ID = FINISH_PRODUCT_SAMPLE + ID;
  public static final String FINISH_PRODUCT_SAMPLE_BY_MIX_DESIGN_CODE =
      FINISH_PRODUCT_SAMPLE + SLASH + "mix-design-code" + SLASH + "{mixDesignCode}";
  public static final String FINISH_PRODUCT_SAMPLE_BY_CONCRETE_MIXER_ID =
      FINISH_PRODUCT_SAMPLE + SLASH + "concrete-mixer-id" + SLASH + "{concreteMixerId}";
  public static final String FINISH_PRODUCT_SAMPLE_SEARCH = FINISH_PRODUCT_SAMPLE + SEARCH;
  public static final String FINISH_PRODUCT_SAMPLES_BY_PLANT_CODE =
      FINISH_PRODUCT_SAMPLES + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * Concrete Mixer apis
   */
  public static final String CONCRETE_MIXER = BASE_API_PATH + "concrete-mixer";
  public static final String CONCRETE_MIXERS = BASE_API_PATH + "concrete-mixers";
  public static final String CONCRETE_MIXER_BY_ID = CONCRETE_MIXER + ID;
  public static final String CONCRETE_MIXER_BY_PLANT_CODE =
      CONCRETE_MIXER + SLASH + "plant" + SLASH + "{plantCode}";
  public static final String CONCRETE_MIXER_SEARCH = CONCRETE_MIXER + SEARCH;
  /*
   * Cube Test Finding
   */
  public static final String CUBE_TEST_FINDING = BASE_API_PATH + "cube-test-finding";
  public static final String CUBE_TEST_FINDINGS = BASE_API_PATH + "cube-test-findings";
  public static final String CUBE_TEST_FINDING_BY_ID = CUBE_TEST_FINDING + ID;
  public static final String CUBE_TEST_FINDING_BY_FINISH_PRODUCT_SAMPLE_ID =
      CUBE_TEST_FINDING + SLASH + "finish-product-sample" + SLASH + "{finishProductSampleId}";
  public static final String CUBE_TEST_FINDING_BY_CONCRETE_TEST_ID =
      CUBE_TEST_FINDING + SLASH + "concrete-test" + SLASH + "{concreteTestId}";
  public static final String SEARCH_CUBE_TEST_FINDING = CUBE_TEST_FINDING + SEARCH;

  /*
   * finish product sample issue apis
   * 
   */
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE =
      BASE_API_PATH + "finish-product-sample-issue";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES =
      BASE_API_PATH + "finish-product-sample-issues";
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_BY_ID = FINISH_PRODUCT_SAMPLE_ISSUE + ID;
  public static final String FINISH_PRODUCT_SAMPLE_ISSUE_SEARCH =
      FINISH_PRODUCT_SAMPLE_ISSUE + SEARCH;
  public static final String FINISH_PRODUCT_SAMPLE_ISSUES_BY_PLANT_CODE =
      FINISH_PRODUCT_SAMPLE_ISSUES + SLASH + "plant" + SLASH + "{plantCode}";
  /*
   * incoming samples count APIs
   */
  public static final String INCOMING_SAMPLES_COUNT = BASE_API_PATH + "incoming-samples-count";
  public static final String INCOMING_SAMPLES_COUNT_BY_MATERIAL =
      BASE_API_PATH + "incoming-samples-count-by-material";
  public static final String MATERIAL_SAMPLE_COUNT_BY_MATERIAL_CATEGORY =
      INCOMING_SAMPLES_COUNT_BY_MATERIAL + SLASH + "{materialCategoryName}";

  public static final String SUB_CATEGORY = "materialSubCategory";
  public static final String MATERIAL_SAMPLE_COUNT_BY_MATERIAL_SUB_CATEGORY =
      INCOMING_SAMPLES_COUNT_BY_MATERIAL + SLASH + SUB_CATEGORY + SLASH
          + "{materialSubCategoryName}";
  public static final String MATERIAL_SUB_CATEGORY_STATUS_COUNT = INCOMING_SAMPLES_COUNT + SLASH
      + "material-sub-category" + SLASH + "{materialSubCategoryName}";
  public static final String MATERIAL_CATEGORY_STATUS_COUNT =
      INCOMING_SAMPLES_COUNT + SLASH + "material-category" + SLASH + "{materialCategoryName}";
  /*
   * concreteTest
   * 
   */
  public static final String CONCRETE_TEST = BASE_API_PATH + "concrete-test";
  public static final String CONCRETE_TESTS = BASE_API_PATH + "concrete-tests";
  public static final String CONCRETE_TEST_BY_ID = CONCRETE_TEST + ID;
  public static final String SEARCH_CONCRETE_TEST = CONCRETE_TEST + SEARCH;
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
   * qualityParameterMaterial
   */
  public static final String MATERIAL_QUALITY_PARAMETER =
      BASE_API_PATH + "material-quality-parameter";
  public static final String MATERIAL_QUALITY_PARAMETER_BY_ID = MATERIAL_QUALITY_PARAMETER + ID;
  public static final String MATERIAL_QUALITY_PARAMETERS =
      BASE_API_PATH + "material-quality-parameters";
  /*
   * concreteTestType
   */
  public static final String CONCRETE_TEST_TYPE = BASE_API_PATH + "concrete-test-type";
  public static final String CONCRETE_TEST_TYPES = BASE_API_PATH + "concrete-test-types";
  public static final String CONCRETE_TEST_TYPE_BY_ID = CONCRETE_TEST_TYPE + ID;
  public static final String SEARCH_CONCRETE_TEST_TYPE = CONCRETE_TEST_TYPE + SEARCH;
  /*
   * concreteTestResult
   * 
   */
  public static final String CONCRETE_TEST_RESULT = BASE_API_PATH + "concrete-test-result";
  public static final String CONCRETE_TEST_RESULTS = BASE_API_PATH + "concrete-test-results";
  public static final String CONCRETE_TEST_RESULT_BY_ID = CONCRETE_TEST_RESULT + ID;
  public static final String SEARCH_CONCRETE_TEST_RESULT = CONCRETE_TEST_RESULT + SEARCH;
  public static final String CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_ID =
      CONCRETE_TEST_RESULT + SLASH + "concrete-test" + SLASH + "{concreteTestId}";
  public static final String CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_TYPE_ID =
      CONCRETE_TEST_RESULT + SLASH + "concrete-test-type" + SLASH + "{concreteTestTypeId}";
  
  /*
   * concreteTestResult
   * 
   */
  public static final String CONCRETE_TEST_RESULT_BY_FINISH_PRODUCT_SAMPLE_ID =
      CONCRETE_TEST_RESULT + SLASH + "finish-product-sample" + SLASH + "{finishProductSampleId}";
  public static final String CONCRETE_TEST_RESULT_BY_CONCRETE_TEST_TYPE_ID_AND_FINISH_PRODUCT_SAMPLE_ID =
      CONCRETE_TEST_RESULT + SLASH + "concrete-test-type" + SLASH + "{concreteTestTypeId}" + SLASH
          + "finish-product-sample" + SLASH + "{finishProductSampleId}";

  /*
   * AdmixtureAcceptedValue Apis
   */
  public static final String ADMIXTURE_ACCEPTED_VALUE = BASE_API_PATH + "admixture-accepted-value";
  public static final String ADMIXTURE_ACCEPTED_VALUES =
      BASE_API_PATH + "admixture-accepted-values";
  public static final String ADMIXTURE_ACCEPTED_VALUE_BY_ID = ADMIXTURE_ACCEPTED_VALUE + ID;
  public static final String ADMIXTURE_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID =
      ADMIXTURE_ACCEPTED_VALUE + "/test-configure" + "/{testConfigureId}";
  public static final String SEARCH_ADMIXTURE_ACCEPTED_VALUE = ADMIXTURE_ACCEPTED_VALUE + SEARCH;
  /*
   * AcceptedValue
   */
  public static final String ACCEPTED_VALUE = BASE_API_PATH + "accepted-value";
  public static final String ACCEPTED_VALUES = BASE_API_PATH + "accepted-values";
  public static final String ACCEPTED_VALUE_BY_ID = ACCEPTED_VALUE + ID;
  public static final String GET_ACCEPTED_VALUE_BY_TEST_CONFIGURE_ID =
      ACCEPTED_VALUE + SLASH + "test-configure" + SLASH + "{testConfigureId}";
  public static final String SEARCH_ACCEPTED_VALUE = ACCEPTED_VALUE + SEARCH;

  /*
   * test Report
   */
  public static final String TEST_REPORT = BASE_API_PATH + "test-report";
  public static final String MATERIAL_TEST_CODE = "material-test" + SLASH + "{materialTestCode}";
  public static final String MATERIAL_TEST_REPORT = TEST_REPORT + SLASH + MATERIAL_TEST_CODE;

  /*
   * concreteTestStatus
   * 
   */
  public static final String CONCRETE_TEST_STATUS = BASE_API_PATH + "concrete-test-status";
  public static final String CONCRETE_TEST_STATUSES = BASE_API_PATH + "concrete-test-statuses";
  public static final String CONCRETE_TEST_STATUS_BY_ID = CONCRETE_TEST_STATUS + ID;
  public static final String SEARCH_CONCRETE_TEST_STATUS = CONCRETE_TEST_STATUS + SEARCH;
  public static final String CONCRETE_TEST_STATUS_BY_CONCRETE_STATUS =
      CONCRETE_TEST_STATUS + SLASH + "concrete-status" + SLASH + "{concreteStatus}";
  public static final String CONCRETE_TEST_STATUS_BYCONCRETE_TEST_TYPE =
      CONCRETE_TEST_STATUS + SLASH + "concrete-test-type" + SLASH + "{concreteTestType}";

  public static final String INCOMING_CODE = "incoming-sample" + SLASH + "{icomingSampleCode}";
  public static final String MATERIAL_TEST_DETAIL_REPORT = TEST_REPORT + SLASH + INCOMING_CODE;
  public static final String TEST_REPORT_DETAIL = BASE_API_PATH + "test-detail-report";
  public static final String MATERIAL_TEST_REPORT_DETAIL =
      TEST_REPORT_DETAIL + SLASH + MATERIAL_TEST_CODE;
  /*
   * test Report
   */
  public static final String SIEVE_TEST_CODE = "sieve-test" + SLASH + "{sieveTestCode}";
  public static final String SIEVE_TEST_REPORT = TEST_REPORT + SLASH + SIEVE_TEST_CODE;
  public static final String CONCRETE_STRENGTH = BASE_API_PATH + SLASH + "concrete-type" + SLASH
      + "{concreteTestType}" + SLASH + "concrete-name" + SLASH + "{concreteTestName}";
  public static final String CEMENT_REPORT_DETAIL =
      TEST_REPORT_DETAIL + SLASH + "cement" + SLASH + MATERIAL_TEST_CODE;
  public static final String FINISH_PRODUCT_SAMPLE_ID =
      "finish-product" + SLASH + "{finishProductSampleId}";
  public static final String FINISH_PRODUCT_RESULT_REPORT =
      TEST_REPORT + SLASH + FINISH_PRODUCT_SAMPLE_ID;
  public static final String FINISH_PRODUCT_RESULTS = "all-results";
  public static final String FINISH_PRODUCT_ALL_RESULTS_REPORT =
      TEST_REPORT + SLASH + FINISH_PRODUCT_RESULTS;
  public static final String CONCRETE_TEST_STATUS_BY_CONCRETE_STATUS_AND_CONCRETE_TEST_TYPE =
      CONCRETE_TEST_STATUS + SLASH + "concrete-status" + SLASH + "{concreteStatus}" + SLASH
          + "concrete-test-type" + SLASH + "{concreteTestType}";
  public static final String INCOMING_SAMPLE_REPORT_DETAIL =
      BASE_API_PATH + SLASH + "incoming-sample-delivery-report" + SLASH + "{incomingSampleCode}";
  public static final String INCOMING_SAMPLE_REPORT_DETAILS =
      BASE_API_PATH + SLASH + "incoming-sample-delivery-report" + SLASH + "{incomingSampleCode}"
          + SLASH + "test-name" + SLASH + "{testName}";
  /*
   * File Export
   */
  public static final String EXPORT_MIXDESIGN = BASE_API_PATH + SLASH + "CSV" + SLASH + "download";
  public static final String UPLOAD_MIXDESIGN = BASE_API_PATH + SLASH + "CSV" + SLASH + "upload";
  public static final String MAIL_REPORT = BASE_API_PATH + "mail-report";

  private EndpointURI() {}
}
