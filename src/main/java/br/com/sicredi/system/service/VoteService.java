package br.com.sicredi.system.service;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sicredi.system.exception.BusinessException;
import br.com.sicredi.system.exception.NotFoundException;
import br.com.sicredi.system.model.Associate;
import br.com.sicredi.system.model.Vote;
import br.com.sicredi.system.model.VotingSession;
import br.com.sicredi.system.model.dto.error.ErrorDetailDto;
import br.com.sicredi.system.model.dto.vote.VoteRequestDto;
import br.com.sicredi.system.model.enumerated.AssociateStatusEnum;
import br.com.sicredi.system.repository.VoteRepository;

@Service
@Transactional
public class VoteService {
	
	@Autowired
	private ErrorDetailDto errorDetailDto;	
	@Autowired
	private MessageService messageService;
	@Autowired
	private VotingSessionService votingSessionService;
	@Autowired
	private AssociateService associateService;	
	@Autowired
	private VoteRepository voteRepository;
	
	public Vote voting(VoteRequestDto voteRequestDto) {
		Vote vote = null;
		Boolean[] alreadyVoted = {Boolean.FALSE};
		//Validar se o CPF foi informado.
		if(voteRequestDto.getCpf() == null || "".equals(voteRequestDto.getCpf())) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.field.cpf", this.messageService.getMessage("message.validate.required.associate.cpf"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.validate.required"),  Arrays.asList(this.errorDetailDto));
		}
		//Validar se o titulo da sessão foi informado.
		if(voteRequestDto.getVotingSessionTitle() == null || "".equals(voteRequestDto.getVotingSessionTitle())) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.field.sessionTitle", this.messageService.getMessage("message.validate.required.votingSession.title"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.validate.required"),  Arrays.asList(this.errorDetailDto));
		}
		//Validar se opção de voto foi informado.
		if(String.valueOf(voteRequestDto.isOptionVote()) == null) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.field.sessionTitle", this.messageService.getMessage("message.validate.required.vote.optionVote"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.validate.required"),  Arrays.asList(this.errorDetailDto));
		}
		
		Associate associate = this.associateService.findByCPF(voteRequestDto.getCpf());
		//Validar se o associado está cadastrado. 
		if(associate == null) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.associate", this.messageService.getMessage("message.business.not.found.associate.does.not.registred", voteRequestDto.getCpf()));
			
			throw new NotFoundException(this.messageService.getMessage("message.title.business.not.found"),  Arrays.asList(this.errorDetailDto));
		}		
		//Validar se o associado tem direito a voto.
		if(AssociateStatusEnum.UNABLE_TO_VOTE.name().equals(associate.getStatus())) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.associate.status", this.messageService.getMessage("message.business.validation.vote.not.permitted"));
			
			throw new BusinessException(this.messageService.getMessage("message.title.business.validation"),  Arrays.asList(this.errorDetailDto));
		}
		
		VotingSession votingSession = this.votingSessionService.findVotingSessionsBySessionTitle(voteRequestDto.getVotingSessionTitle());
		//Validar se a sessão de votação está cadastrada.
		if(votingSession == null) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.votingSession", this.messageService.getMessage("message.business.not.found.votingSession.does.not.registred", voteRequestDto.getVotingSessionTitle()));
			
			throw new NotFoundException(this.messageService.getMessage("message.title.business.not.found"),  Arrays.asList(this.errorDetailDto));
		}
		//Validar se a sessão de votação está fechada.
		if(votingSession.getVotationClosedDate().isBefore(LocalDateTime.now()) && votingSession.getMessageSend()) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.votationClosedDate", this.messageService.getMessage("message.business.not.found.votingSession.closed", voteRequestDto.getVotingSessionTitle()));
			
			throw new NotFoundException(this.messageService.getMessage("message.title.business.not.found"),  Arrays.asList(this.errorDetailDto));
		}
		
		votingSession.getVotes().forEach(voteByAssociate -> {
			if(associate.getCpf().equals(voteByAssociate.getAssociate().getCpf())) {
				alreadyVoted[0] = Boolean.TRUE;
			}
		});
		//Validar se o associado já votou.
		if(alreadyVoted[0]) {
			this.errorDetailDto = new ErrorDetailDto("VoteService.voting.alreadyVoted", this.messageService.getMessage("message.business.validation.associate.already.voted", voteRequestDto.getCpf()));
			
			throw new BusinessException(this.messageService.getMessage("message.title.business.validation"),  Arrays.asList(this.errorDetailDto));
		}
		
		vote = new Vote();
		BeanUtils.copyProperties(voteRequestDto, vote);
		vote.setAssociate(associate);		
		vote = this.voteRepository.save(vote);
		votingSession.getVotes().add(vote);
		this.votingSessionService.registerVote(votingSession);
		
		return vote;
	}
}
