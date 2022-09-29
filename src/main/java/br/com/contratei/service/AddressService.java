package br.com.contratei.service;

import br.com.contratei.dto.AddressDto;

import java.util.List;

public interface AddressService {

    void createAddress(AddressDto addressDto);

    List<AddressDto> findAllByConsumerId(int consumerId);

    List<AddressDto> findAllByProviderId(int providerId);

    void deleteById(int id);

    void changeAddress(int providerId, AddressDto addres);
}
