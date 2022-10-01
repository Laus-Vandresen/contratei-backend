package br.com.contratei.service.impl;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.repository.BudgetRepository;
import br.com.contratei.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository repository;

    @Override
    public BudgetDto findById(Integer id) {
        return new BudgetDto(repository.findById(id).orElse(null));
    }

    @Override
    public BudgetEntity save(BudgetDto dto) {
        return repository.save(new BudgetEntity(dto));
    }

    @Override
    public Page<BudgetDto> findByConsumer(int page, int size, int consumerId, BudgetStatusEnum status) {
        return repository.findByConsumer(PageRequest.of(page, size), consumerId, status);
    }

    @Override
    public Boolean checkExistenceBudget(int consumerId, int providerId) {
        return !repository.findAllByConsumerIdAndProviderId(consumerId, providerId).isEmpty();
    }

    @Override
    public BudgetDto changeBudget(int budgetId, BudgetDto budget) {
        Optional<BudgetEntity> budgetEntity = repository.findById(budgetId);
        if (budgetEntity.isPresent()) {
            budgetEntity.get().changeCoreData(budget);
            repository.save(budgetEntity.get());
        }
        return budget;
    }
}
