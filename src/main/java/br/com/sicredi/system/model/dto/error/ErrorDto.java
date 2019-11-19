package br.com.sicredi.system.model.dto.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	
	private String title;
	
	private List<ErrorDetailDto> errorDetailsDto;
	
	public ErrorDto() {
		super();
	}

	public ErrorDto(int statusCode, String title, List<ErrorDetailDto> errorDetailsDto) {
		super();
		this.statusCode = statusCode;
		this.title = title;
		this.errorDetailsDto = errorDetailsDto;
	}

	public ErrorDto(int statusCode, String title) {
		super();
		this.statusCode = statusCode;
		this.title = title;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ErrorDetailDto> getErrorDetailsDto() {
		if(this.errorDetailsDto == null) {
			this.errorDetailsDto = new ArrayList<ErrorDetailDto>();
		}
		return this.errorDetailsDto;
	}
}
