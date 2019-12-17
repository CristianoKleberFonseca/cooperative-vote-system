package br.com.sicredi.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.controller.VoteController;
import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.dto.associate.AssociateResponsetDto;
import br.com.sicredi.system.model.dto.vote.VoteRequestDto;
import br.com.sicredi.system.model.dto.vote.VoteResponseDto;
import br.com.sicredi.system.service.VoteService;

@RestController
public class VoteControllerImpl implements VoteController {
	
	@Autowired
	private VoteService voteService;

	@Override
	public ResponseEntity<VoteResponseDto> voting(@Valid @RequestBody VoteRequestDto vote) {
		VoteResponseDto voteResponseDto = new VoteResponseDto();
		
		Vote voteResponse = this.voteService.voting(vote);
		BeanUtils.copyProperties(voteResponse, voteResponseDto);
		AssociateResponsetDto associateResponsetDto = new AssociateResponsetDto();
		BeanUtils.copyProperties(voteResponse.getAssociate(), associateResponsetDto);
		voteResponseDto.setAssociate(associateResponsetDto);
		return new ResponseEntity<VoteResponseDto>(voteResponseDto, HttpStatus.CREATED);
	}

}
