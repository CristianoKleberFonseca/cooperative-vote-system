package br.com.sicredi.system.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sicredi.system.broker.output.AdapterPublishAssemblyResultBroker;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;
import br.com.sicredi.system.service.VotingSessionService;

@Component
public class VotingSessionSchedule {

	@Autowired
	private VotingSessionService votingSessionService;
	
	@Autowired
	private AdapterPublishAssemblyResultBroker adapterPublishAssemblyResultBroker; 
	
	@Scheduled(cron = "0/30 * * * * *")
	public void closeVotingSession() {
		List<VotingSessionResultDto> votingSessionResultDtoList = null;
		
		votingSessionResultDtoList = this.votingSessionService.closeVotingSession();
		if(votingSessionResultDtoList != null && votingSessionResultDtoList.size() > 0) {
			this.adapterPublishAssemblyResultBroker.sendMessageAssemblyResult(votingSessionResultDtoList);
		} else {
			//TODO: Deverá ser logado a ausência de registro no votação.
		}
		
	}
}
