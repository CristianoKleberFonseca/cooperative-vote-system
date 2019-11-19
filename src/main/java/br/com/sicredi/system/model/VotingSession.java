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
	private LocalDateTime dateCreation;
	
//	@Column(name="votation_closed_date")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime votationClosedDate;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Vote> votes;
	
	private boolean opened;

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

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
}
