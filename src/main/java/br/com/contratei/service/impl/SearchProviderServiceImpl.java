package br.com.contratei.service.impl;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.repository.ProviderUserRepository;
import br.com.contratei.service.SearchProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchProviderServiceImpl implements SearchProviderService {

    @Autowired
    private ProviderUserRepository repository;

    @Override
    public List<ProviderUserDto> findByName(String prefix) {
        return repository.findByFirstNameStartsWithOrderByKmWorkRangeAsc(prefix).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType) {
        return repository.findByServiceTypeOrderByKmWorkRangeAsc(serviceType).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<ProviderUserDto> findPageable(int page, int size) {
        return repository.findPageable(PageRequest.of(page, size));
    }
}
