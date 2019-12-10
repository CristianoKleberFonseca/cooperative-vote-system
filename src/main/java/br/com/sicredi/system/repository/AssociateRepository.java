package br.com.sicredi.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.system.model.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long>{
	
	public Associate findByCpf(String cpf);

}
