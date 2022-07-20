package br.com.contratei.service;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.entity.BugetEntity;

public interface BugetService {

    BugetEntity findById(Integer id);
    BugetEntity save(BugetDto dto);
}
