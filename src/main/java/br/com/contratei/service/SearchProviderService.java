package br.com.contratei.service;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Provider;
import java.util.List;

public interface SearchProviderService {

    List<ProviderUserDto> findByName(String prefix);

    List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType);

    Page<ProviderUserDto> findPageable(int page, int size);
}
