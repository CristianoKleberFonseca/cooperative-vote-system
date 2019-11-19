package br.com.sicredi.system.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionRequestDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResponseDto;
import br.com.sicredi.system.repository.VotingSessionRepository;

@Service
public class VotingSessionService {
//	@Autowired
//	private VotingSessionRepository votingSessionRepository;

	public VotingSessionResponseDto create(VotingSessionRequestDto votingSessionDto) {
		VotingSessionResponseDto votingSessionResponseDto = null;

		if (votingSessionDto.getSessionTitle() == null || "".equals(votingSessionDto.getSessionTitle())) {
			// TODO: Inserir mensagem de tratamento.
		} else if (votingSessionDto.getTimeDuration() < 1) {
			// TODO: Inserir mensagem de tratamento.
		}
		votingSessionResponseDto = new VotingSessionResponseDto();
		BeanUtils.copyProperties(this.buildVotingSession(votingSessionDto), votingSessionResponseDto);
//		BeanUtils.copyProperties(this.votingSessionRepository.save(this.buildVotingSession(votingSessionDto)), votingSessionResponseDto);

		return votingSessionResponseDto;
	}

	public List<VotingSessionResponseDto> findVotingSessionsToDateCreation(LocalDate dateCreation) {
		List<VotingSessionResponseDto> votingSessionResponseDtoList = null;

		return votingSessionResponseDtoList;
	}

	private VotingSession buildVotingSession(VotingSessionRequestDto votingSessionRequestDto) {
		VotingSession votingSession = new VotingSession();

		BeanUtils.copyProperties(votingSessionRequestDto, votingSession);
		votingSession.setDateCreation(LocalDateTime.now());
		if (votingSessionRequestDto.getTimeDuration() == null || votingSessionRequestDto.getTimeDuration() == 0) {
			votingSession.setVotationClosedDate(votingSession.getDateCreation().plusMinutes(1));
		} else {
			votingSession.setVotationClosedDate(
					votingSession.getDateCreation().plusMinutes(votingSessionRequestDto.getTimeDuration()));
		}
		votingSession.setVotes(new ArrayList<Vote>());

		return votingSession;
	}
}
