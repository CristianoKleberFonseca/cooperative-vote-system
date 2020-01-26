package br.com.sicredi.system.model.dto.votingsession;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VotingSessionResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String RESULT_YES_WINNER = "A quantidade de votos SIM superou a quantidade de votos NÃO.";
	
	public static final String RESULT_NO_WINNER = "A quantidade de votos NÃO superou a quantidade de votos SIM.";
	
	public static final String RESULT_DRAW = "Ocorreu um empate entre os votos SIM e NÃO";
	
	private String sessionTitle; 
	
	private String result;

	private LocalDateTime votationCreateDate;
	
	private LocalDateTime votationClosedDate;

	private Integer qtdYes;
	
	private Integer qtdNo;

	public String getSessionTitle() {
		return sessionTitle;
	}

	public void setSessionTitle(String sessionTitle) {
		this.sessionTitle = sessionTitle;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public LocalDateTime getVotationCreateDate() {
		return votationCreateDate;
	}

	public void setVotationCreateDate(LocalDateTime votationCreateDate) {
		this.votationCreateDate = votationCreateDate;
	}

	public LocalDateTime getVotationClosedDate() {
		return votationClosedDate;
	}

	public void setVotationClosedDate(LocalDateTime votationClosedDate) {
		this.votationClosedDate = votationClosedDate;
	}

	public Integer getQtdYes() {
		return qtdYes;
	}

	public void setQtdYes(Integer qtdYes) {
		this.qtdYes = qtdYes;
	}

	public Integer getQtdNo() {
		return qtdNo;
	}

	public void setQtdNo(Integer qtdNo) {
		this.qtdNo = qtdNo;
	}

	@Override
	public String toString() {
		StringBuilder votingSessionResult = new StringBuilder();

		votingSessionResult.append("{");
		votingSessionResult.append("\"sessionTitle\":").append("\"").append(this.sessionTitle).append("\"");
		votingSessionResult.append("\"votationCreateDate\":").append("\"").append(this.votationCreateDate).append("\"");
		votingSessionResult.append("\"votationClosedDate\":").append("\"").append(this.votationClosedDate).append("\"");
		votingSessionResult.append("\"result\":").append("\"").append(this.result).append("\"");
		votingSessionResult.append("}");
		
		return votingSessionResult.toString();
	}
}
