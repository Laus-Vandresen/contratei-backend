package br.com.contratei.service.impl;

import br.com.contratei.dto.CreditCardDto;
import br.com.contratei.entity.CreditCardEntity;
import br.com.contratei.repository.CreditCardRepository;
import br.com.contratei.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    @Override
    public List<CreditCardDto> findAllByConsumerId(int consumerId) {
        return repository.findAllByConsumerId(consumerId).stream().map(CreditCardDto::new).collect(Collectors.toList());
    }

    @Override
    public void createCreditCard(CreditCardDto dto) {
        repository.save(new CreditCardEntity(dto));
    }

    @Override
    public CreditCardDto findById(int id) {
        return new CreditCardDto(repository.findById(id).get());
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
