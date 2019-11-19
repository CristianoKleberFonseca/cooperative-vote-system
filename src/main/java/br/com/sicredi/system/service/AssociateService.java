package br.com.sicredi.system.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.system.adapter.inbound.AssociateAdapterInbound;
import br.com.sicredi.system.model.Associate;
import br.com.sicredi.system.model.dto.associate.AssociateRequestDto;
import br.com.sicredi.system.model.dto.associate.AssociateResponsetDto;
import br.com.sicredi.system.model.dto.external.ResultHerokuapp;
import br.com.sicredi.system.model.enumerated.AssociateStatusEnum;

@Service
public class AssociateService {

	@Autowired
	private AssociateAdapterInbound associateAdapterInbound;
//	@Autowired
//	private AssociateRepository associateRepository;
	
	public AssociateResponsetDto insert(AssociateRequestDto associateRequestDto) {
		AssociateResponsetDto associateResponseDto = null;
		Associate associate = null;
		
		if(associateRequestDto.getCpf() == null || "".equals(associateRequestDto.getCpf())) {
			// TODO: Inserir mensagem de tratamento.
		} else if(associateRequestDto.getName() == null || "".equals(associateRequestDto.getName())) {
			// TODO: Inserir mensagem de tratamento.
		}
		associate = new Associate();
		BeanUtils.copyProperties(associateRequestDto, associate);
		associate.setStatus(this.callHerokuapService(associateRequestDto.getCpf()).name());
		associateResponseDto = new AssociateResponsetDto();
		BeanUtils.copyProperties(associate, associateResponseDto);
//		BeanUtils.copyProperties(this.associateRepository.save(associate), associateResponseDto);			
		return associateResponseDto;
	}
	
	private AssociateStatusEnum callHerokuapService(String cpf) {
		ResultHerokuapp resultHerokuapp = null;		
		
		resultHerokuapp = this.associateAdapterInbound.callHerokuappService(cpf);		
		
		return AssociateStatusEnum.valueOf(resultHerokuapp.getStatus());
	}
}
