package br.com.sicredi.system.model.dto.external;

import java.io.Serializable;

public class ResultHerokuapp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8123431305388067403L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
