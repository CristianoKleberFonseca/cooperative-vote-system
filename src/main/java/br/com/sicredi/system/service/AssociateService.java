package br.com.sicredi.system.service;

import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.system.adapter.inbound.AssociateAdapterInbound;
import br.com.sicredi.system.exception.BusinessException;
import br.com.sicredi.system.model.Associate;
import br.com.sicredi.system.model.dto.associate.AssociateRequestDto;
import br.com.sicredi.system.model.dto.error.ErrorDetailDto;
import br.com.sicredi.system.model.dto.external.ResultHerokuapp;
import br.com.sicredi.system.model.enumerated.AssociateStatusEnum;
import br.com.sicredi.system.repository.AssociateRepository;

@Service
@Transactional
public class AssociateService {
	
	@Autowired
	private ErrorDetailDto errorDetailDto;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private AssociateAdapterInbound associateAdapterInbound;
	
	@Autowired
	private AssociateRepository associateRepository;
			
	public Associate insert(AssociateRequestDto associateRequestDto) {
		Associate associate = null;
		
		if(associateRequestDto.getCpf() == null || "".equals(associateRequestDto.getCpf())) {
			this.errorDetailDto = new ErrorDetailDto("AssociateSrvice.insert.field.cpf", this.messageService.getMessage("message.validate.required.associate.cpf"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		} else if(associateRequestDto.getName() == null || "".equals(associateRequestDto.getName())) {
			this.errorDetailDto = new ErrorDetailDto("AssociateSrvice.insert.field.name", this.messageService.getMessage("message.validate.required.associate.name"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto)); 
		}
		associate = new Associate();
		BeanUtils.copyProperties(associateRequestDto, associate);
		associate.setStatus(this.callHerokuapService(associateRequestDto.getCpf()).name());
		associate = this.associateRepository.save(associate);
		return associate;
	}
	
	public Associate findByCPF(String cpf) {
		Associate associate = null;
		
		if(cpf == null || "".equals(cpf)) {
			this.errorDetailDto = new ErrorDetailDto("AssociateSrvice.findByCPF.field.cpf", this.messageService.getMessage("message.validate.required.associate.cpf"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.fiel.required"),  Arrays.asList(this.errorDetailDto));
		}
		
		associate = this.associateRepository.findByCpf(cpf);
		
		return associate;
	}
	
	private AssociateStatusEnum callHerokuapService(String cpf) {
		ResultHerokuapp resultHerokuapp = null;		
		
		resultHerokuapp = this.associateAdapterInbound.callHerokuappService(cpf);		
		
		return AssociateStatusEnum.valueOf(resultHerokuapp.getStatus());
	}
}
