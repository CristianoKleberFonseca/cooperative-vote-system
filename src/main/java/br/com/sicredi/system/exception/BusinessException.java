package br.com.sicredi.system.exception;

import java.util.List;

import br.com.sicredi.system.model.dto.error.ErrorDetailDto;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private List<ErrorDetailDto> errorDetailsDto;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, List<ErrorDetailDto> details) {
		super(message);
		this.errorDetailsDto = details;
	}
	
	public BusinessException(List<ErrorDetailDto> details) {
		this.errorDetailsDto = details;
	}
	
	public List<ErrorDetailDto> getDetails() {
		return this.errorDetailsDto;
	}
}
