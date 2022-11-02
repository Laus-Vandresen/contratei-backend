package br.com.contratei.service;

import br.com.contratei.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {

    List<CreditCardDto> findAllByConsumerId(int consumerId);
    void createCreditCard(CreditCardDto dto);
    CreditCardDto findById(int id);
    void deleteById(int id);
}
