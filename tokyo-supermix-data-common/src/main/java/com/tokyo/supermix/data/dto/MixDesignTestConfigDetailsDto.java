package com.tokyo.supermix.data.dto;

import java.util.List;

public class MixDesignTestConfigDetailsDto {
	private RatioConfigResponseDto ratioConfigResponseDto;
	private List<RatioConfigParameterResponseDto> ratioConfigParameterResponseDto;
	private List<RatioConfigEquationResponseDto> ratioConfigEquationResponseDto;

	public RatioConfigResponseDto getRatioConfigResponseDto() {
		return ratioConfigResponseDto;
	}

	public void setRatioConfigResponseDto(RatioConfigResponseDto ratioConfigResponseDto) {
		this.ratioConfigResponseDto = ratioConfigResponseDto;
	}

	public List<RatioConfigParameterResponseDto> getRatioConfigParameterResponseDto() {
		return ratioConfigParameterResponseDto;
	}

	public void setRatioConfigParameterResponseDto(
			List<RatioConfigParameterResponseDto> ratioConfigParameterResponseDto) {
		this.ratioConfigParameterResponseDto = ratioConfigParameterResponseDto;
	}

	public List<RatioConfigEquationResponseDto> getRatioConfigEquationResponseDto() {
		return ratioConfigEquationResponseDto;
	}

	public void setRatioConfigEquationResponseDto(List<RatioConfigEquationResponseDto> ratioConfigEquationResponseDto) {
		this.ratioConfigEquationResponseDto = ratioConfigEquationResponseDto;
	}
}
