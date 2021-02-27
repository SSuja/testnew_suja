package com.tokyo.supermix.data.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeRequestDto {
	private Long id;
	@NotNull(message = "{employeeDto.firstName.null}")
	@NotEmpty(message = "{employeeDto.firstName.empty}")
	private String firstName;
	@NotNull(message = "{employeeDto.lastName.null}")
	@NotEmpty(message = "{employeeDto.lastName.empty}")
	private String lastName;
	@NotNull(message = "{employeeDto.email.null}")
	@NotEmpty(message = "{employeeDto.email.empty}")
	private String email;
	private String phoneNumber;
	private String address;
	private String plantCode;
	private Long designationId;
	private boolean hasUser;
	private boolean isEnabled;
	private String profilePicPath;
	private boolean sentMail;
}
