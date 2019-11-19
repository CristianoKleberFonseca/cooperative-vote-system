package br.com.sicredi.system.exception;

import java.util.Arrays;
import java.util.List;

import br.com.sicredi.system.model.dto.error.ErrorDetailDto;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ErrorDetailDto> errorDetailsDto;
	
	public BadRequestException(String message, ErrorDetailDto ... details) {
		super(message);
		if(details != null) {
			this.errorDetailsDto = Arrays.asList(details);
		}
	}
	
	public BadRequestException(List<ErrorDetailDto> details) {
		super("Bad Request");
		this.errorDetailsDto = details;
	}
	
	public List<ErrorDetailDto> getDetails() {
		return this.errorDetailsDto;
	}
}
