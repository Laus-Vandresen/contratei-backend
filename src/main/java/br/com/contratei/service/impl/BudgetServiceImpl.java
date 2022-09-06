package br.com.contratei.service.impl;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.repository.BudgetRepository;
import br.com.contratei.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    BudgetRepository repository;

    @Override
    public BudgetEntity findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BudgetEntity save(BudgetDto dto) {
        return repository.save(new BudgetEntity(dto));
    }

    @Override
    public Page<BudgetDto> findByConsumer(int page, int size, int consumerId) {
        return repository.findByConsumer(PageRequest.of(page, size), consumerId);
    }
}
