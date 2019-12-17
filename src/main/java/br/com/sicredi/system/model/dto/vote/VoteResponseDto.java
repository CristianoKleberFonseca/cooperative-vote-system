package br.com.sicredi.system.model.dto.vote;

import java.io.Serializable;

import br.com.sicredi.system.model.dto.associate.AssociateResponsetDto;

public class VoteResponseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long idVote;

	private boolean optionVote;
	
	private AssociateResponsetDto associate;

	public Long getIdVote() {
		return idVote;
	}

	public void setIdVote(Long idVote) {
		this.idVote = idVote;
	}

	public boolean isOptionVote() {
		return optionVote;
	}

	public void setOptionVote(boolean optionVote) {
		this.optionVote = optionVote;
	}

	public AssociateResponsetDto getAssociate() {
		return associate;
	}

	public void setAssociate(AssociateResponsetDto associate) {
		this.associate = associate;
	}
}
