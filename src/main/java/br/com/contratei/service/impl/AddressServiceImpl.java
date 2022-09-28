package br.com.contratei.service.impl;

import br.com.contratei.dto.AddressDto;
import br.com.contratei.entity.AddressEntity;
import br.com.contratei.repository.AddressRepository;
import br.com.contratei.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public void createAddress(AddressDto addressDto) {
        repository.save(new AddressEntity(addressDto));
    }

    @Override
    public List<AddressDto> findAllByConsumerId(int consumerId) {
        return repository.findAllByConsumerId(consumerId).stream().map(AddressDto::new).collect(Collectors.toList());
    }

    @Override
    public List<AddressDto> findAllByProviderId(int providerId) {
        return repository.findAllByProviderId(providerId).stream().map(AddressDto::new).collect(Collectors.toList());
    }
}
