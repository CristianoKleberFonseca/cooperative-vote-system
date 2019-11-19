package br.com.sicredi.system.model.dto.votingsession;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotingSessionResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String sessionTitle; 
	
	private LocalDateTime dateCreation;
	
	private LocalDateTime votationClosedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public LocalDateTime getVotationClosedDate() {
		return votationClosedDate;
	}

	public void setVotationClosedDate(LocalDateTime votationClosedDate) {
		this.votationClosedDate = votationClosedDate;
	}
}
