package br.com.contratei.repository;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.enuns.BudgetStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBudgetRepository {

    Page<BudgetDto> findByConsumer(Pageable page, int consumerId, BudgetStatusEnum status);
}
