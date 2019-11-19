package br.com.sicredi.system.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.system.controller.AssociateController;
import br.com.sicredi.system.model.dto.associate.AssociateRequestDto;
import br.com.sicredi.system.model.dto.associate.AssociateResponsetDto;
import br.com.sicredi.system.service.AssociateService;

@RestController
public class AssociateControllerImpl implements AssociateController {
	
	@Autowired
	private AssociateService associateService;

	@Override
	public ResponseEntity<AssociateResponsetDto> insert(@Valid @RequestBody AssociateRequestDto associate) {
		AssociateResponsetDto associateResponsetDto = null;
		associateResponsetDto = this.associateService.insert(associate); 
		return new ResponseEntity<AssociateResponsetDto>(associateResponsetDto, HttpStatus.CREATED);
	}

}
