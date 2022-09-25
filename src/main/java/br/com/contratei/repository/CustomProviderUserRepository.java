package br.com.contratei.repository;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProviderUserRepository {

    Page<ProviderUserDto> findPageable(Pageable page, ServiceTypeEnum serviceType);
}
