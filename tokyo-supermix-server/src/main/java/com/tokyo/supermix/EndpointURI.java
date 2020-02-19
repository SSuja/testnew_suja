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
  public static final String UPDATE_PLANT = PLANT + CODE;
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
  public static final String SUPPLIER_CATEGORIES = BASE_API_PATH + "supplier-categories";
  public static final String SUPPLIER_CATEGORY = BASE_API_PATH + "supplier-category";

  private EndpointURI() {

  }
}
