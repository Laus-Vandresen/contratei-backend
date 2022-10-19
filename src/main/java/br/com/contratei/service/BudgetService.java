package br.com.contratei.service;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import org.springframework.data.domain.Page;

public interface BudgetService {

    BudgetEntity findById(Integer id);
    BudgetEntity save(BudgetDto dto);
    void save(BudgetEntity entity);
    Page<BudgetDto> findByConsumer(int page, int size, int consumerId, BudgetStatusEnum status);
    Page<BudgetDto> findByProvider(int page, int size, int providerId, BudgetStatusEnum status);
    Page<BudgetDto> findOpenBudgets(int page, int size, int providerId, PriorityLevelEnum priorityLevel);
    Boolean checkExistenceBudget(int consumerId, int providerId);
    BudgetDto changeBudget(int budgetId, BudgetDto budget);
}
