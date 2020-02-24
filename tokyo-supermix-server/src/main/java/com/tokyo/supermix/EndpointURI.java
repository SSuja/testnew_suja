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
	 * Unit apis
	 */
	public static final String UNIT = BASE_API_PATH + "unit";
	public static final String UNITS = BASE_API_PATH + "units";
	public static final String GET_UNIT_BY_ID = UNIT + ID;
	public static final String DELETE_UNIT_BY_ID = UNIT + ID;

	private EndpointURI() {

	}
}
