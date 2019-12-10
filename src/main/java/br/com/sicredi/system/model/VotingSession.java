package br.com.sicredi.system.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="VotingSession")
@Table(name="voting_session")
public class VotingSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idVotingSession;
	
	@Column(name="session_title")
	private String sessionTitle; 
	
	@Column(name="votation_create_date")
	private LocalDateTime votationCreateDate;
	
	@Column(name="votation_closed_date")
	private LocalDateTime votationClosedDate;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "Vote_Voting_Session")
	private List<Vote> votes;
	
	@Column(name="message_send")
	private Boolean messageSend;

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

	public LocalDateTime getVationCreateDate() {
		return votationCreateDate;
	}

	public void setVationCreateDate(LocalDateTime votationCreateDate) {
		this.votationCreateDate = votationCreateDate;
	}

	public LocalDateTime getVotationClosedDate() {
		return votationClosedDate;
	}

	public void setVotationClosedDate(LocalDateTime votationClosedDate) {
		this.votationClosedDate = votationClosedDate;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public Boolean getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(Boolean messageSend) {
		this.messageSend = messageSend;
	}
}
