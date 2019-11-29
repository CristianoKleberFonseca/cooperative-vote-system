package br.com.sicredi.system.broker.output;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sicredi.system.config.broker.BrokerOutput;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;

@Component
@EnableBinding({BrokerOutput.class})
public class AdapterPublishAssemblyResultBroker {
	
	@Autowired
	BrokerOutput brokerOutput;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public boolean sendMessageAssemblyResult(List<VotingSessionResultDto> votingSessionResultDtoList) {
		String message = null;
		boolean sendMessage = false;
		try {
			message = this.objectMapper.writeValueAsString(votingSessionResultDtoList);
			sendMessage = brokerOutput.publishExchangeAssemblyResult().send(MessageBuilder.withPayload(message).build());
		} catch (JsonProcessingException e) {
			// TODO: Incluir LOG de erro da transformação da mensagem em string.
		}		
		return sendMessage;
	}
}