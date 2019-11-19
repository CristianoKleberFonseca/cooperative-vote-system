package br.com.sicredi.system.adapter.inbound;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.system.exception.BadRequestException;
import br.com.sicredi.system.exception.NotFoundException;
import br.com.sicredi.system.model.dto.error.ErrorDetailDto;
import br.com.sicredi.system.model.dto.external.ResultHerokuapp;
import br.com.sicredi.system.service.MessageService;

@Component
public class AssociateAdapterInbound {
	
	private RestTemplate restTemplate;
	@Value("${service.urls.herokuapp}")
	private String urlService;	
	private ErrorDetailDto errorDetailDto;
	@Autowired
	private MessageService messageService;
	
	public ResultHerokuapp callHerokuappService(String cpf) {
		ResultHerokuapp resultHerokuappReturn = null;
		StringBuilder url = new StringBuilder();
		
		try {
			url.append(this.urlService);
			url.append(cpf);
			this.restTemplate = new RestTemplate();
			resultHerokuappReturn =  this.restTemplate.getForObject(url.toString(), ResultHerokuapp.class);
		} catch (HttpClientErrorException ex) {
			this.errorDetailDto = new ErrorDetailDto("AssociateAdapterInbound.callHerokuappService", ex.getMessage());
			throw new NotFoundException(this.messageService.getMessage("message.title.invalid.cpf"),  Arrays.asList(this.errorDetailDto));
		} catch (HttpServerErrorException ex) {
			this.errorDetailDto = new ErrorDetailDto("AssociateAdapterInbound.callHerokuappService", ex.getMessage());
			throw new BadRequestException(this.messageService.getMessage("message.title.invalid.cpf"), this.errorDetailDto);
		}
		return resultHerokuappReturn;
	}

}
