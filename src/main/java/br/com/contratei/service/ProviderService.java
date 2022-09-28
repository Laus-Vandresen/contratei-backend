package br.com.contratei.service;

import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.PhotoDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProviderService {

    List<ProviderUserDto> findByName(String prefix);

    List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType);

    ProviderUserDto findById(int id);

    Page<ProviderUserDto> findPageable(int page, int size, ServiceTypeEnum serviceType);

    void recalculateScore(int providerId);

    ProviderUserDto changeProviderUser(int providerId, CoreProviderDto coreProvider);

    List<PhotoDto> findPhotosProvider(int providerId);
}
