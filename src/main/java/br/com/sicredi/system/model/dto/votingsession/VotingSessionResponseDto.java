package br.com.sicredi.system.model.dto.votingsession;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotingSessionResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long idVotingSession;
	
	private String sessionTitle; 
	
	private LocalDateTime votationCreateDate;
	
	private LocalDateTime votationClosedDate;

	public Long getIdVotingSession() {
		return idVotingSession;
	}

	public void setIdVotingSession(Long idVotingSession) {
		this.idVotingSession = idVotingSession;
	}

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public LocalDateTime getVotationCreateDate() {
		return votationCreateDate;
	}

	public void setVotationCreateDate(LocalDateTime votationCreateDate) {
		this.votationCreateDate = votationCreateDate;
	}

	public LocalDateTime getVotationClosedDate() {
		return votationClosedDate;
	}

	public void setVotationClosedDate(LocalDateTime votationClosedDate) {
		this.votationClosedDate = votationClosedDate;
	}
}
