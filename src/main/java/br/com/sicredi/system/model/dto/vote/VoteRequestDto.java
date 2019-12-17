package br.com.sicredi.system.model.dto.vote;

import java.io.Serializable;

public class VoteRequestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean optionVote;
	
	private String cpf;
	
	private String votingSessionTitle;

	public boolean isOptionVote() {
		return optionVote;
	}

	public void setOptionVote(boolean optionVote) {
		this.optionVote = optionVote;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getVotingSessionTitle() {
		return votingSessionTitle;
	}

	public void setVotingSessionTitle(String votingSessionTitle) {
		this.votingSessionTitle = votingSessionTitle;
	}
}
