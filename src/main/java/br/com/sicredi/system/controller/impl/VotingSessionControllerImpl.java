package br.com.sicredi.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.controller.VotingSessionController;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionRequestDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResponseDto;
import br.com.sicredi.system.service.VotingSessionService;

@RestController
public class VotingSessionControllerImpl implements VotingSessionController {
	
	@Autowired
	private VotingSessionService votingSessionService;

	@Override
	public ResponseEntity<VotingSessionResponseDto> create(@Valid @RequestBody VotingSessionRequestDto votingSession) {
		VotingSessionResponseDto votingSessionResponseDto = new VotingSessionResponseDto();
		
		BeanUtils.copyProperties(this.votingSessionService.create(votingSession), votingSessionResponseDto);
		
		return new ResponseEntity<VotingSessionResponseDto>(votingSessionResponseDto, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<VotingSessionResponseDto> findVotingSessionsBySessionTitle(String sessionTitle) {
		VotingSessionResponseDto votingSessionResponseDto = new VotingSessionResponseDto();
		
		BeanUtils.copyProperties(this.votingSessionService.findVotingSessionsBySessionTitle(sessionTitle), votingSessionResponseDto);
		
		return new ResponseEntity<VotingSessionResponseDto>(votingSessionResponseDto, HttpStatus.OK);
	}

}
