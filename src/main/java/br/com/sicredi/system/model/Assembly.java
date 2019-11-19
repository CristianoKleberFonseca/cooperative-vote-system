package br.com.sicredi.system.model;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Entity(name="Assembly")
//@Table(name="assembly")
public class Assembly implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="")
	private String title;
	
//	@Column(name="")
//	@Temporal(TemporalType.DATE)
	private LocalDateTime dateCreation;
	
//	@OneToOne
//	@JoinColumn(name="voting_session_id")
	private VotingSession votingSession;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public VotingSession getVotingSession() {
		return votingSession;
	}

	public void setVotingSession(VotingSession votingSession) {
		this.votingSession = votingSession;
	}
}
