package br.com.sicredi.system.model;

import java.io.Serializable;

//@Entity(name="Vote")
//@Table(name="vote")
public class Vote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="cpf")
	private boolean option;
	
//	@OneToOne
//	@JoinColumn(name="associated_id")
	private Associate associated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public Associate getAssociated() {
		return associated;
	}

	public void setAssociated(Associate associated) {
		this.associated = associated;
	}
}
