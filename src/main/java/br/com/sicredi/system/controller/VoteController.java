package br.com.sicredi.system.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.model.dto.error.ErrorDto;
import br.com.sicredi.system.model.dto.vote.VoteRequestDto;
import br.com.sicredi.system.model.dto.vote.VoteResponseDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/vote", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Vote")
public interface VoteController {

	@PostMapping({"/v1.0.0/ballot"})
	@ApiOperation(value = "Create a ballot", 
		nickname = "VotingSessionCreate", 
		notes = "Represent the associate's vote on a determined assembly.", 
		response = VotingSessionResponseDto.class, 
		authorizations = {}, 
		tags = {"Endpoint Vote"})
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Voting realized with success.", response = VotingSessionResponseDto.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDto.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDto.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorDto.class),
			@ApiResponse(code = 422, message = "Unprocessable Entity", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDto.class)
	})
	public ResponseEntity<VoteResponseDto> voting(VoteRequestDto vote);
}
