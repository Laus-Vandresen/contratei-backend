package br.com.contratei.service.impl;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.repository.BudgetRepository;
import br.com.contratei.service.BudgetService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private BudgetRepository repository;

    @Autowired
    private ProviderService providerService;

    @Override
    public BudgetEntity findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BudgetEntity save(BudgetDto dto) {
        return repository.save(new BudgetEntity(dto));
    }

    @Override
    public void save(BudgetEntity entity) {
        repository.save(entity);
    }

    @Override
    public Page<BudgetDto> findByConsumer(int page, int size, int consumerId, BudgetStatusEnum status) {
        return repository.findByConsumer(PageRequest.of(page, size), consumerId, status);
    }

    @Override
    public Page<BudgetDto> findByProvider(int page, int size, int providerId, BudgetStatusEnum status) {
        return repository.findByProvider(PageRequest.of(page, size), providerId, status);
    }

    @Override
    public Page<BudgetDto> findOpenBudgets(int page, int size, int providerId, PriorityLevelEnum priorityLevel) {
        var provider = providerService.findById(providerId);
        return repository.findOpenBudgets(PageRequest.of(page, size), provider, priorityLevel);
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
