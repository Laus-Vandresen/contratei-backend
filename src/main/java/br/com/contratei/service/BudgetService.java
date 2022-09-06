package br.com.contratei.service;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import org.springframework.data.domain.Page;

public interface BudgetService {

    BudgetEntity findById(Integer id);
    BudgetEntity save(BudgetDto dto);
    Page<BudgetDto> findByConsumer(int page, int size, int consumerId);
}
