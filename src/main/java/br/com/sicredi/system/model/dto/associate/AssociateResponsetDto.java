package br.com.sicredi.system.model.dto.associate;

import java.io.Serializable;

public class AssociateResponsetDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idAssociate;
	
	private String cpf;
	
	private String name;
	
	private String status;

	public Long getIdAssociate() {
		return idAssociate;
	}

	public void setIdAssociate(Long idAssociate) {
		this.idAssociate = idAssociate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
