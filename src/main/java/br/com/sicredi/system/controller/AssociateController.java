package br.com.sicredi.system.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.model.dto.associate.AssociateRequestDto;
import br.com.sicredi.system.model.dto.associate.AssociateResponsetDto;
import br.com.sicredi.system.model.dto.error.ErrorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/associate", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Associate")
public interface AssociateController {

	@PostMapping({"/v1.0.0/insert"})
	@ApiOperation(value = "Register Associate", 
		nickname = "AssociateInsert", 
		notes = "Register one associate on cooperative that will able to vote.", 
		response = AssociateResponsetDto.class, 
		authorizations = {}, 
		tags = {"Endpoint Associate"})
	@ApiResponses(value= {
			@ApiResponse(code = 200, message = "OK", response = AssociateResponsetDto.class),
			@ApiResponse(code = 201, message = "Associate registered with success.", response = AssociateResponsetDto.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorDto.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = ErrorDto.class),
			@ApiResponse(code = 403, message = "Forbidden", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorDto.class),
			@ApiResponse(code = 422, message = "Unprocessable Entity", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDto.class)
	})
	public ResponseEntity<AssociateResponsetDto> insert(AssociateRequestDto associate);
}
