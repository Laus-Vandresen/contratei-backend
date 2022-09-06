package br.com.contratei.repository;

import br.com.contratei.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Integer>, CustomBudgetRepository {
}
