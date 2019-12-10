package br.com.sicredi.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.system.model.VotingSession;

@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {
	
	public VotingSession findVotingSessionBySessionTitle(String sessionTitle);
	
	public List<VotingSession> findVotingSessionByMessageSend(Boolean messageSend);

}
