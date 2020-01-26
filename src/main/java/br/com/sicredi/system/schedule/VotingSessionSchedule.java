package br.com.sicredi.system.schedule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.sicredi.system.broker.output.AdapterPublishAssemblyResultBroker;
import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.votingsession.VotingSessionResultDto;
import br.com.sicredi.system.service.VotingSessionService;

@Component
public class VotingSessionSchedule {

	private static final Logger LOGGER = LoggerFactory.getLogger(VotingSessionSchedule.class);
	@Autowired
	private VotingSessionService votingSessionService;	
	@Autowired
	private AdapterPublishAssemblyResultBroker adapterPublishAssemblyResultBroker; 
	
	@Scheduled(cron = "0/30 * * * * *")
	public void closeVotingSession() {
		List<VotingSession> votingSessionClosedList = this.votingSessionService.closeVotingSession();
		
		if(votingSessionClosedList != null && votingSessionClosedList.size() > 0) {
			votingSessionClosedList.forEach(votingSession -> {
				if(votingSession.getVotes() != null && votingSession.getVotes().size() > 0) {
					VotingSessionResultDto votingSessionResultDto = new VotingSessionResultDto();
					BeanUtils.copyProperties(votingSession, votingSessionResultDto);
					this.processResult(votingSession.getVotes(), votingSessionResultDto);
					this.adapterPublishAssemblyResultBroker.sendMessageAssemblyResult(votingSessionResultDto);
				}
			});
		} else {
			LOGGER.info("Don't exist voting session register to send.");
		}
	}
	
	private void processResult(List<Vote> votesList, VotingSessionResultDto votingSessionResultDto) {
		votesList.forEach(vote -> {
			if(vote.isOptionVote()) {
				votingSessionResultDto.setQtdYes((votingSessionResultDto.getQtdYes() == null ? 1 : votingSessionResultDto.getQtdYes()+1));
			} else {
				votingSessionResultDto.setQtdNo((votingSessionResultDto.getQtdNo() == null ? 1 : votingSessionResultDto.getQtdNo()+1));				
			}
		});
		if(votingSessionResultDto.getQtdYes() > votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULT_YES_WINNER);
		} else if(votingSessionResultDto.getQtdYes() < votingSessionResultDto.getQtdNo()) {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULT_NO_WINNER);
		} else {
			votingSessionResultDto.setResult(VotingSessionResultDto.RESULT_DRAW);
		}
	}
}
