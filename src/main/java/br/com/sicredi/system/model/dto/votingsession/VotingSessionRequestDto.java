package br.com.sicredi.system.model.dto.votingsession;

import java.io.Serializable;

public class VotingSessionRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sessionTitle; 
	
	private Long timeDuration;
	
	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public Long getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(Long timeDuration) {
		this.timeDuration = timeDuration;
	}
}
