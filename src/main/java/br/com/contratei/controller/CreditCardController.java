package br.com.contratei.controller;

import br.com.contratei.dto.CreditCardDto;
import br.com.contratei.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-card")
public class CreditCardController {

    @Autowired
    private CreditCardService service;

    @GetMapping()
    public CreditCardDto findById(@RequestParam int id) {
        return service.findById(id);
    }

    @GetMapping("find-by-consumer-id")
    public List<CreditCardDto> findAllByConsumerId(@RequestParam int consumerId) {
        return service.findAllByConsumerId(consumerId);
    }

    @PostMapping()
    public void createCreditCard(@RequestBody CreditCardDto dto) {
        service.createCreditCard(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCreditCard(@PathVariable int id) {
        service.deleteById(id);
    }

}
