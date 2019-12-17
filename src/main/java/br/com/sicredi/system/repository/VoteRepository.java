package br.com.sicredi.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.system.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
