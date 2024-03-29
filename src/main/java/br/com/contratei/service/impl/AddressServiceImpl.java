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

    @Override
    public AddressDto findMainByConsumerId(int consumerId) {
        var consumerAddres = repository.findAllByConsumerIdAndMainIsTrue(consumerId);
        return new AddressDto(consumerAddres.get(0));
    }

    @Override
    public AddressDto findByProviderIdAndMainIsTrue(int providerId) {
        var addressEntity = repository.findByProviderIdAndMainIsTrue(providerId);
        return new AddressDto(addressEntity);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void changeAddress(int providerId, AddressDto addres) {
        var addresEntity = repository.findById(providerId);
        addresEntity.get().changeCoreData(addres);
        repository.save(addresEntity.get());
    }
}
