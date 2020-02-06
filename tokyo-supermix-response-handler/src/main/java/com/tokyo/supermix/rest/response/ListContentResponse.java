package com.tokyo.supermix.rest.response;

import java.util.List;

import com.tokyo.supermix.rest.enums.RestApiResponseStatus;

/**
 * Generic of API content response as a list
 */
public class ListContentResponse<T> extends ApiResponse {
  public ListContentResponse(RestApiResponseStatus restApiResponseStatus) {
    super(restApiResponseStatus);
  }
  
  private List<T> content;

  public ListContentResponse(List<T> content) {
    super(RestApiResponseStatus.OK);
    this.content = content;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }
}
