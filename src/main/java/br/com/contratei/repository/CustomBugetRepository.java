package br.com.contratei.repository;

import br.com.contratei.dto.BugetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBugetRepository {

    Page<BugetDto> findByConsumer(Pageable page, int consumerId);
}
