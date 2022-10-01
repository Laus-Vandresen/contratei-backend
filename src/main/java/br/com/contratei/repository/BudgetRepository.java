package br.com.contratei.repository;

import br.com.contratei.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Integer>, CustomBudgetRepository {

    List<BudgetEntity> findAllByConsumerIdAndProviderId(int consumerId, int providerId);
}
