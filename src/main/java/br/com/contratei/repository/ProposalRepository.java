package br.com.contratei.repository;

import br.com.contratei.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Integer> {
}
