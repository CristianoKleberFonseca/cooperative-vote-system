package br.com.sicredi.system.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.model.dto.error.ErrorDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionRequestDto;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/votingsession", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Voting Session")
public interface VotingSessionController {

	@PostMapping({"/v1.0.0/create"})
	@ApiOperation(value = "Create Voting Sessionn", 
		nickname = "VotingSessionCreate", 
		notes = "Open one voting session to receive the VotingSessions's votes.", 
		response = VotingSessionResponseDto.class, 
		authorizations = {}, 
		tags = {"Endpoint Voting Session"})
	@ApiResponses(value= {
			@ApiResponse(code = 201, message = "Create one voting session with success.", response = VotingSessionResponseDto.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDto.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDto.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorDto.class),
			@ApiResponse(code = 422, message = "Unprocessable Entity", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDto.class)
	})
	public ResponseEntity<VotingSessionResponseDto> create(VotingSessionRequestDto votingSession);
	
	@GetMapping({"/v1.0.0/findVotingSessionsToDateCreation"})
	@ApiOperation(value = "Find Voting Sessionn by session title", 
		nickname = "VotingSessionFindFromDate", 
		notes = "Find a voting session list from a session title.", 
		response = VotingSessionResponseDto.class, 
		authorizations = {}, 
		tags = {"Endpoint Voting Session"})
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "Voting session list were found with success.", response = VotingSessionResponseDto.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDto.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDto.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorDto.class),
			@ApiResponse(code = 422, message = "Unprocessable Entity", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDto.class)
	})
	public ResponseEntity<VotingSessionResponseDto> findVotingSessionsBySessionTitle(String sessionTitle);
}
