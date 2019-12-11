package br.com.sicredi.system.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicredi.system.broker.output.AdapterPublishAssemblyResultBroker;
import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;
import br.com.sicredi.system.service.VotingSessionService;

@Component
public class VotingSessionSchedule {

	@Autowired
	private VotingSessionService votingSessionService;
	
	@Autowired
	private AdapterPublishAssemblyResultBroker adapterPublishAssemblyResultBroker; 
	
	//	@Scheduled(cron = "0/30 * * * * *")
	public void closeVotingSession() {
		List<VotingSession> votingSessionClosedList = null;
		
		votingSessionClosedList = this.votingSessionService.closeVotingSession();
		List<VotingSessionResultDto> votingSessionResultDtoList = new ArrayList<VotingSessionResultDto>();
		
		if(votingSessionClosedList != null && votingSessionClosedList.size() > 0) {
			votingSessionClosedList.forEach(votingSession -> {
				VotingSessionResultDto votingSessionResultDto = new VotingSessionResultDto();
				BeanUtils.copyProperties(votingSession, votingSessionResultDto);
//				this.processResult(votingSession.getVotes(), votingSessionResultDto);
				votingSessionResultDtoList.add(votingSessionResultDto);
			});
			this.adapterPublishAssemblyResultBroker.sendMessageAssemblyResult(votingSessionResultDtoList);
		} else {
			//TODO: Deverá ser logado a ausência de registro no votação.
		}
		
	}
	private void processResult(List<Vote> votesList, VotingSessionResultDto votingSessionResultDto) {
		votesList.forEach(vote -> {
			if(vote.isOption()) {
				votingSessionResultDto.setQtdYes((votingSessionResultDto.getQtdYes() == null ? 1 : votingSessionResultDto.getQtdYes()+1));
			} else {
				votingSessionResultDto.setQtdNo((votingSessionResultDto.getQtdNo() == null ? 1 : votingSessionResultDto.getQtdNo()+1));				
			}
		});
		if(votingSessionResultDto.getQtdYes() > votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_SIM_VENCEU);
		} else if(votingSessionResultDto.getQtdYes() < votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_NAO_VENCEU);
		} else {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULTADO_EMPATE);
		}
	}
}
