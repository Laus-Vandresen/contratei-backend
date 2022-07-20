package br.com.contratei.service;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;

import java.security.Provider;
import java.util.List;

public interface SearchProviderService {

    List<ProviderUserDto> findByName(String prefix);

    List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType);
}
