package br.com.sicredi.system.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.system.exception.BusinessException;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.error.ErrorDetailDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionRequestDto;
import br.com.sicredi.system.repository.VotingSessionRepository;

@Service
@Transactional
public class VotingSessionService {
	@Autowired
	private ErrorDetailDto errorDetailDto;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	private List<VotingSession> votingSessionList = null;
	
	public VotingSession create(VotingSessionRequestDto votingSessionDto) {
		VotingSession votingSessionReturn = null;

		if (votingSessionDto.getSessionTitle() == null || "".equals(votingSessionDto.getSessionTitle())) {
			this.errorDetailDto = new ErrorDetailDto("VotingSessionService.create.field.sessionTitle", this.messageService.getMessage("message.validate.required.votingSession.title"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		} else if (votingSessionDto.getTimeDuration() < 0) {
			this.errorDetailDto = new ErrorDetailDto("VotingSessionService.create.field.timeDuration", this.messageService.getMessage("message.validate.negative.votingSession.timeDuration"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		}
		
		votingSessionReturn = this.votingSessionRepository.findVotingSessionBySessionTitle(votingSessionDto.getSessionTitle());
		if(votingSessionReturn != null) {
			this.errorDetailDto = new ErrorDetailDto("VotingSessionService.create.field.timeDuration", this.messageService.getMessage("message.validate.required.votingSession.timeDuration"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		}
		votingSessionReturn = this.buildVotingSession(votingSessionDto);
		votingSessionReturn = this.votingSessionRepository.save(votingSessionReturn);

		return votingSessionReturn;
	}

	public VotingSession findVotingSessionsBySessionTitle(String sessionTitle) {
		VotingSession votingSessionReturn = null;
		
		if(sessionTitle == null || "".equals(sessionTitle)) {
			this.errorDetailDto = new ErrorDetailDto("VotingSessionService.findVotingSessionsBySessionTitle.title", this.messageService.getMessage("message.validate.required.votingSession.title"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		}
		
		votingSessionReturn = this.votingSessionRepository.findVotingSessionBySessionTitle(sessionTitle);
		
		return votingSessionReturn;
	}
	
	public List<VotingSession> findVotingSessionsOpened() {
		List<VotingSession> votingSessionList = null;
		
		votingSessionList = this.votingSessionRepository.findVotingSessionByMessageSend(Boolean.FALSE);
		votingSessionList.stream().filter(votingSession -> votingSession.getVotationClosedDate().isBefore(LocalDateTime.now()));
		
		return votingSessionList;
	}
	
	public List<VotingSession> closeVotingSession() {
		List<VotingSession> votingSessionList = null;
		this.votingSessionList = null;
		
		votingSessionList = this.findVotingSessionsOpened();
		if(votingSessionList != null && votingSessionList.size() > 0) {
			votingSessionList.forEach(votingSession -> {
				votingSession.setMessageSend(Boolean.TRUE);
			this.votingSessionRepository.save(votingSession);
			});
		}
		return this.votingSessionList;
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
}
