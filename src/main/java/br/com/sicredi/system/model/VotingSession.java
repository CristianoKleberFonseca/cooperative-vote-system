package br.com.sicredi.system.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

//@Entity(name="VotingSession")
//@Table(name="voting_session")
public class VotingSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="session_ title")
	private String sessionTitle; 
	
//	@Column(name="date_creation")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime votationCreateDate;
	
//	@Column(name="votation_closed_date")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime votationClosedDate;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vote> votes;
	
	private Boolean messageSend;

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
