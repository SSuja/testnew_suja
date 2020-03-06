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
	public static final String GET_PLANT_BY_CODE = PLANT + CODE;
	public static final String PLANTS = BASE_API_PATH + "plants";

	/*
	 * Designation API
	 */
	public static final String DESIGNATION = BASE_API_PATH + "designation";
	public static final String DESIGNATIONS = BASE_API_PATH + "designations";
	public static final String GET_DESIGNATION_BY_ID = DESIGNATION + ID;
	public static final String DELETE_DESIGNATION_BY_ID = DESIGNATION + ID;

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
	/*
	 * Employee apis
	 */
	public static final String EMPLOYEE = BASE_API_PATH + "employee";
	public static final String DELETE_EMPLOYEE = EMPLOYEE + ID;
	public static final String GET_EMPLOYEE_BY_ID = EMPLOYEE + ID;
	public static final String UPDATE_EMPLOYEE = EMPLOYEE;
	public static final String EMPLOYEES = BASE_API_PATH + "employees";
	/*
	 * Material Category APIs
	 */
	public static final String MATERIAL_CATEGORY = BASE_API_PATH + "material-category";
	public static final String MATERIAL_CATEGORIES = BASE_API_PATH + "material-categories";
	public static final String GET_MATERIAL_CATEGORY_BY_ID = MATERIAL_CATEGORY + ID;
	public static final String DELETE_MATERIAL_CATEGORY = MATERIAL_CATEGORY + ID;
	/*
	 * Material Category APIs
	 */
	public static final String POUR = BASE_API_PATH + "pour";
	public static final String POURS = BASE_API_PATH + "pours";
	public static final String GET_POUR_BY_ID = POUR + ID;
	public static final String DELETE_POUR = POUR + ID;
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
	public static final String GET_MATERIAL_SUB_CATEGORY_BY_MATERIAL_CATEGORY = MATERIAL_SUB_CATEGORY + "/category"
			+ MATERIAL_CATEGORY_ID;
	public static final String GET_MATERIAL_SUB_CATEGORY_BY_ID = MATERIAL_SUB_CATEGORY + ID;
	public static final String DELETE_MATERIAL_SUB_CATEGORY = MATERIAL_SUB_CATEGORY + ID;
	/*
	 * Equipment APIs
	 */
	public static final String EQUIPMENT = BASE_API_PATH + "equipment";
	public static final String EQUIPMENTS = BASE_API_PATH + "equipments";
	public static final String DELETE_EQUIPMENT = EQUIPMENT + ID;
	public static final String GET_EQUIPMENT_BY_ID = EQUIPMENT + ID;

	/*
	 * Test
	 */
	public static final String TEST = BASE_API_PATH + "test";
	public static final String TESTS = BASE_API_PATH + "tests";
	public static final String GET_TEST_BY_ID = TEST + ID;
	public static final String DELETE_TEST = TEST + ID;
	public static final String GET_TEST_BY_TEST_TYPE_ID =
	      TEST + SLASH + "test-type" + SLASH + "{testTypeId}";
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

	/*
	 * EquipmentPlantCalibration apis
	 */
	public static final String EQUIPMENT_PLANT_CALIBRATION = BASE_API_PATH + "plant-equipment-calibration";
	public static final String EQUIPMENT_PLANT_CALIBRATIONS = BASE_API_PATH + "plant-equipment-calibrations";
	public static final String GET_EQUIPMENT_PLANT_CALIBRATION_BY_ID = EQUIPMENT_PLANT_CALIBRATION + ID;
	public static final String DELETE_EQUIPMENT_PLANT_CALIBRATION = EQUIPMENT_PLANT_CALIBRATION + ID;

	/*
	 * PlantEquipment
	 * 
	 */
	public static final String PLANTEQUIPMENT = BASE_API_PATH + "plantequipment";
	public static final String PLANTEQUIPMENTS = BASE_API_PATH + "plantequipments";
	public static final String DELETE_PLANTEQUIPMENT = PLANTEQUIPMENT + SERIALNO;
	public static final String GET_PLANTEQUIPMENT_BY_SERIALNO = PLANTEQUIPMENT + SERIALNO;

	/*
	 * Customer apis
	 */
	public static final String CUSTOMER = BASE_API_PATH + "customer";
	public static final String CUSTOMERS = BASE_API_PATH + "customers";
	public static final String DELETE_CUSTOMER = CUSTOMER + ID;
	public static final String GET_CUSTOMER_BY_ID = CUSTOMER + ID;

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

	/*
	 * TestType
	 */
	public static final String TEST_TYPE = BASE_API_PATH + "test-type";
	public static final String DELETE_TEST_TYPE = TEST_TYPE + ID;
	public static final String GET_TEST_TYPE_BY_ID = TEST_TYPE + ID;
	public static final String TEST_TYPES = BASE_API_PATH + "test-types";

	/*
	 * IncomingSample Apis
	 */
	public static final String INCOMING_SAMPLE = BASE_API_PATH + "incoming-sample";
	public static final String INCOMING_SAMPLES = BASE_API_PATH + "incoming-samples";
	public static final String INCOMING_SAMPLE_BY_CODE = INCOMING_SAMPLE + CODE;
	public static final String INCOMING_SAMPLE_BY_CODE_AND_STATUS = INCOMING_SAMPLE_BY_CODE + "/test-status"
			+ "/{status}";
	/*
	 * Project APIs
	 */
	public static final String PROJECT = BASE_API_PATH + "project";
	public static final String PROJECT_BY_ID = PROJECT + CODE;
	public static final String PROJECTS = BASE_API_PATH + "projects";

	/*
	 * Mix design apis
	 * 
	 */
	public static final String MIX_DESIGN = BASE_API_PATH + "mix-design";
	public static final String MIX_DESIGNS = BASE_API_PATH + "mix-designs";
	public static final String MIX_DESIGN_BY_CODE = MIX_DESIGN + CODE;
	/*
	 * ProcessSampleLoad Apis
	 */
	public static final String PROCESS_SAMPLE_LOAD = BASE_API_PATH + "process-sample-load";
	public static final String PROCESS_SAMPLE_LOADS = BASE_API_PATH + "process-sample-loads";
	public static final String PROCESS_SAMPLE_LOAD_BY_ID = PROCESS_SAMPLE_LOAD + ID;
	/*
	 * User
	 */
	public static final String USER = BASE_API_PATH + "user";
	public static final String USERS = BASE_API_PATH + "users";
	public static final String USER_BY_ID = USER + ID;

	/*
	 * Test Parameter APIs
	 * 
	 */
	public static final String TEST_PARAMETER = BASE_API_PATH + "test-parameter";
	public static final String TEST_PARAMETERS = BASE_API_PATH + "test-parameters";
	public static final String TEST_PARAMETER_BY_ID = TEST_PARAMETER + ID;
	public static final String GET_TEST_PARAMETER_BY_TEST_ID = TEST_PARAMETER + "/test" + "/{testId}";

	/*
	 * ProcessSample APIs
	 */
	public static final String PROCESS_SAMPLE = BASE_API_PATH + "process-sample";
	public static final String PROCESS_SAMPLES = BASE_API_PATH + "process-samples";
	public static final String PROCESS_SAMPLE_BY_CODE = PROCESS_SAMPLE + CODE;

	/*
	 * Mix design proportion apis
	 * 
	 */

	public static final String MIX_DESIGN_PROPORTION = BASE_API_PATH + "mix-design-proportion";
	public static final String MIX_DESIGN_PROPORTIONS = BASE_API_PATH + "mix-design-proportions";
	public static final String MIX_DESIGN_PROPORTION_BY_ID = MIX_DESIGN_PROPORTION + ID;
	public static final String MIX_DESIGN_PROPORTION_BY_MIXDESIGNCODE = MIX_DESIGN_PROPORTION + "/mix-design"
			+ "/{mixDesignCode}";

	/*
	 * ConcreteStrengthTest APIs
	 */
	public static final String CONCRETE_STRENGTH_TEST = BASE_API_PATH + "concrete-strength-test";
	public static final String CONCRETE_STRENGTH_TESTS = BASE_API_PATH + "concrete-strength-tests";
	public static final String CONCRETE_STRENGTH_TEST_BY_ID = CONCRETE_STRENGTH_TEST + ID;
	/*
	 * concreteTest
	 * 
	 */
	public static final String CONCRETE_TEST = BASE_API_PATH + "concrete-test";
	public static final String CONCRETE_TESTS = BASE_API_PATH + "concrete-tests";
	public static final String CONCRETE_TEST_BY_ID = CONCRETE_TEST + ID;

	/*
	 * Finish Product APIs
	 */
	public static final String FINISH_PRODUCT = BASE_API_PATH + "finish-product";
	public static final String FINISH_PRODUCTS = BASE_API_PATH + "finish-products";
	public static final String FINISH_PRODUCT_BY_ID = FINISH_PRODUCT + ID;
/*
   * Equation Parameter
   */
  public static final String EQUATION_PARAMETER = BASE_API_PATH + "equation-parameter";
  public static final String EQUATION_PARAMETER_BY_ID = EQUATION_PARAMETER + ID;
  public static final String EQUATION_PARAMETERS = BASE_API_PATH + "equation-parameters";
  public static final String EQUATION_ID="/{equationId}";
  public static final String GET_PARAMETERS_BY_EQUATION_ID =
      EQUATION_PARAMETER + "/equationparameter" + EQUATION_ID;
	private EndpointURI() {
	}
}
