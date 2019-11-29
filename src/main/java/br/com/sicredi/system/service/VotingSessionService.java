package br.com.sicredi.system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionRequestDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResponseDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;
import br.com.sicredi.system.repository.VotingSessionRepository;

@Service
@Transactional
public class VotingSessionService {
//	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	private List<VotingSessionResultDto> votingSessionResultDtoList = null;
	
	public VotingSessionResponseDto create(VotingSessionRequestDto votingSessionDto) {
		VotingSessionResponseDto votingSessionResponseDto = null;
		VotingSession votingSession = null;

		if (votingSessionDto.getSessionTitle() == null || "".equals(votingSessionDto.getSessionTitle())) {
			// TODO: Inserir mensagem de tratamento.
		} else if (votingSessionDto.getTimeDuration() < 1) {
			// TODO: Inserir mensagem de tratamento.
		}
		
		votingSession = this.votingSessionRepository.findVotingSessionBySessionTitle(votingSessionDto.getSessionTitle());
		if(votingSession != null) {
			// TODO: Inserir mensagem de tratamento.
		} else {
			votingSession = this.buildVotingSession(votingSessionDto);
			votingSessionResponseDto = new VotingSessionResponseDto();
			BeanUtils.copyProperties(votingSession, votingSessionResponseDto);
//		this.votingSessionRepository.save(votingSession);
		}

		return votingSessionResponseDto;
	}

	public VotingSessionResponseDto findVotingSessionsBySessionTitle(String sessionTitle) {
		VotingSessionResponseDto votingSessionResponseDto = null;
		VotingSession votingSession = null;
		
		if(sessionTitle == null || "".equals(sessionTitle)) {
			
		}
		votingSession = this.votingSessionRepository.findVotingSessionBySessionTitle(sessionTitle);
		if(votingSession == null) {
			// TODO: Inserir mensagem de tratamento.
		}
		BeanUtils.copyProperties(votingSession, votingSessionResponseDto);
		
		return votingSessionResponseDto;
	}
	
	public List<VotingSession> findVotingSessionsOpened() {
		List<VotingSession> votingSessionList = null;
		
		//TODO: Após construção da camada repository retirar o comentário abaixo e a instanciação do objeto feito na mão.
//		votingSessionList = this.votingSessionRepository.findVotingSessionByMessageSend(Boolean.FALSE);
		VotingSession votingSessionMock = new VotingSession();
		votingSessionMock.setId(1L);
		votingSessionMock.setMessageSend(Boolean.FALSE);
		votingSessionMock.setSessionTitle("Votação Teste");
		votingSessionMock.setVationCreateDate(LocalDateTime.now());
		
		Vote vote1 = new Vote();
		vote1.setId(1L);
		vote1.setOption(Boolean.TRUE);
		Vote vote2 = new Vote();
		vote2.setId(2L);
		vote2.setOption(Boolean.FALSE);
		Vote vote3 = new Vote();
		vote3.setId(3L);
		vote3.setOption(Boolean.FALSE);
		votingSessionMock.setVotes(new ArrayList<Vote>());
		votingSessionMock.getVotes().add(vote1);
		votingSessionMock.getVotes().add(vote2);
		votingSessionMock.getVotes().add(vote3);
		votingSessionList = new ArrayList<VotingSession>();
		votingSessionList.add(votingSessionMock);
		
		if(votingSessionList == null || votingSessionList.size() == 0) {
			// TODO: Inserir mensagem de tratamento.
		}
		
		votingSessionList.stream().filter(votingSession -> votingSession.getVotationClosedDate().isBefore(LocalDateTime.now()));
		
		return votingSessionList;
	}
	
	public List<VotingSessionResultDto> closeVotingSession() {
		List<VotingSession> votingSessionList = null;
		this.votingSessionResultDtoList = null;
		
		votingSessionList = this.findVotingSessionsOpened();
		if(votingSessionList != null && votingSessionList.size() > 0) {
			this.votingSessionResultDtoList = new ArrayList<VotingSessionResultDto>();		
			votingSessionList.forEach(votingSession -> {
				votingSession.setMessageSend(Boolean.TRUE);
				VotingSessionResultDto votingSessionResultDto = new VotingSessionResultDto();
				BeanUtils.copyProperties(votingSession, votingSessionResultDto);
				this.processResult(votingSession, votingSessionResultDto);
//			this.votingSessionRepository.save(votingSession);
				this.votingSessionResultDtoList.add(votingSessionResultDto);
			});
		}
		return this.votingSessionResultDtoList;
	}

	private VotingSession buildVotingSession(VotingSessionRequestDto votingSessionRequestDto) {
		VotingSession votingSession = new VotingSession();

		BeanUtils.copyProperties(votingSessionRequestDto, votingSession);
		votingSession.setVationCreateDate(LocalDateTime.now());
		if (votingSessionRequestDto.getTimeDuration() == null || votingSessionRequestDto.getTimeDuration() == 0) {
			votingSession.setVotationClosedDate(votingSession.getVationCreateDate().plusMinutes(1));
		} else {
			votingSession.setVotationClosedDate(votingSession.getVationCreateDate().plusMinutes(votingSessionRequestDto.getTimeDuration()));
		}
		votingSession.setMessageSend(Boolean.FALSE);

		return votingSession;
	}
	
	private void processResult(VotingSession votingSession, VotingSessionResultDto votingSessionResultDto) {
		List<Vote> votesList = votingSession.getVotes();
		votesList.forEach(vote -> {
			if(vote.isOption()) {
				votingSessionResultDto.setQtdYes((votingSessionResultDto.getQtdYes() == null ? 1 : votingSessionResultDto.getQtdYes()+1));
			} else {
				votingSessionResultDto.setQtdNo((votingSessionResultDto.getQtdNo() == null ? 1 : votingSessionResultDto.getQtdNo()+1));				
			}
		});
		if(votingSessionResultDto.getQtdYes() > votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_SIM_VENCEU);
		} else if(votingSessionResultDto.getQtdYes() < votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_NAO_VENCEU);
		} else {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_EMPATE);
		}
	}
}
