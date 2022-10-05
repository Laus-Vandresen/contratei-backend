package br.com.contratei.repository;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBudgetRepository {

    Page<BudgetDto> findByConsumer(Pageable page, int consumerId, BudgetStatusEnum status);

    Page<BudgetDto> findByProvider(Pageable page, int providerId, BudgetStatusEnum status);

    Page<BudgetDto> findOpenBudgets(Pageable page, ServiceTypeEnum serviceType, PriorityLevelEnum priorityLevel);
}
