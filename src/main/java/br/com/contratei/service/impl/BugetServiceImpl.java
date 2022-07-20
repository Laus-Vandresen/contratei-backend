package br.com.contratei.service.impl;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.entity.BugetEntity;
import br.com.contratei.repository.BugetRepository;
import br.com.contratei.service.BugetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BugetServiceImpl implements BugetService {

    @Autowired
    BugetRepository repository;

    @Override
    public BugetEntity findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BugetEntity save(BugetDto dto) {
        return repository.save(new BugetEntity(dto));
    }
}
