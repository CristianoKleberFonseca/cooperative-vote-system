package br.com.sicredi.system.broker.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sicredi.system.config.broker.BrokerOutput;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;
import br.com.sicredi.system.schedule.VotingSessionSchedule;

@Component
@EnableBinding({BrokerOutput.class})
public class AdapterPublishAssemblyResultBroker {
	private static final Logger LOGGER = LoggerFactory.getLogger(VotingSessionSchedule.class);
	@Autowired
	private BrokerOutput brokerOutput;	
	@Autowired
	private ObjectMapper objectMapper;
	
	public boolean sendMessageAssemblyResult(VotingSessionResultDto votingSessionResultDto) {
		String message = null;
		boolean sendMessage = false;
		try {
			message = this.objectMapper.writeValueAsString(votingSessionResultDto);
			sendMessage = brokerOutput.publishExchangeAssemblyResult().send(MessageBuilder.withPayload(message).build());
		} catch (JsonProcessingException e) {
			LOGGER.error(String.format("Occurred an error sending the result: %s", e.getMessage()));
		}		
		return sendMessage;
	}
}