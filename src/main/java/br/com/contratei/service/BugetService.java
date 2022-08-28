package br.com.contratei.service;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.entity.BugetEntity;
import org.springframework.data.domain.Page;

public interface BugetService {

    BugetEntity findById(Integer id);
    BugetEntity save(BugetDto dto);
    Page<BugetDto> findByConsumer(int page, int size, int consumerId);
}
