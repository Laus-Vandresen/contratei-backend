package br.com.contratei.repository;

import br.com.contratei.dto.ProviderUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomProviderUserRepository {

    Page<ProviderUserDto> findPageable(Pageable page);
}
