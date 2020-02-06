package com.tokyo.supermix.rest.response;

import com.tokyo.supermix.rest.enums.RestApiResponseStatus;

public class BasicResponse<T> extends ApiResponse {

	public BasicResponse(RestApiResponseStatus restApiResponseStatus) {
		super(restApiResponseStatus);
	}
	
	public BasicResponse() {
		super(RestApiResponseStatus.OK);
	}
	
	public  BasicResponse(T responseBody, RestApiResponseStatus restApiResponseStatus, String message) {
		super(restApiResponseStatus);
		super.setMessage(message);
	}

}
