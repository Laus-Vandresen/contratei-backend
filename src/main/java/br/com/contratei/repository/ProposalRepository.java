package br.com.contratei.repository;

import br.com.contratei.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Integer> {

    List<ProposalEntity> findAllByBudgetId(int budgetId);

    List<ProposalEntity> findAllByBudgetIdAndIdIsNot(int budgetId, int id);
}
