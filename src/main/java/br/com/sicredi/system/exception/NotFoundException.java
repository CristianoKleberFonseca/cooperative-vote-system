package br.com.sicredi.system.exception;

import java.util.Arrays;
import java.util.List;

import br.com.sicredi.system.model.dto.error.ErrorDetailDto;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ErrorDetailDto> errorDetailsDto;
	
	public NotFoundException(String message) {
		super(message);
	}
	
	public NotFoundException(String message, List<ErrorDetailDto> details) {
		super(message);
		this.errorDetailsDto = details;
	}
	
	public NotFoundException(ErrorDetailDto ... details) {
		super("Bad Request");
		this.errorDetailsDto = Arrays.asList(details);
	}
	
	public List<ErrorDetailDto> getDetails() {
		return this.errorDetailsDto;
	}
}
