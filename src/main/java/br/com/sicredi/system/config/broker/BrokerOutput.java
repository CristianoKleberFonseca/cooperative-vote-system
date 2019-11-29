package br.com.sicredi.system.config.broker;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface BrokerOutput {
	public static final String PUBLISH_EXCHANGE_ASSEMBLY_RESULT = "publishExchangeAssemblyResult";
	
	@Output(BrokerOutput.PUBLISH_EXCHANGE_ASSEMBLY_RESULT)
	public MessageChannel publishExchangeAssemblyResult();
}
