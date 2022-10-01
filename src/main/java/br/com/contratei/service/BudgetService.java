package br.com.contratei.service;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import org.springframework.data.domain.Page;

public interface BudgetService {

    BudgetDto findById(Integer id);
    BudgetEntity save(BudgetDto dto);
    Page<BudgetDto> findByConsumer(int page, int size, int consumerId, BudgetStatusEnum status);
    Boolean checkExistenceBudget(int consumerId, int providerId);
    BudgetDto changeBudget(int budgetId, BudgetDto budget);
}
