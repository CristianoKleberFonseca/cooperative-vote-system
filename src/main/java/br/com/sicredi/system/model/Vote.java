package br.com.sicredi.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name="Vote")
@Table(name="vote")
public class Vote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idVote;
	
	@Column(name="option_vote")
	private boolean optionVote;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_associate")
	private Associate associate;
	
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

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}
}
