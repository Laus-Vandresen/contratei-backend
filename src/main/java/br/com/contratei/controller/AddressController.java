package br.com.contratei.controller;

import br.com.contratei.dto.AddressDto;
import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("/create-address")
    public void createAddress(@RequestBody AddressDto addressDto) {
        service.createAddress(addressDto);
    }

    @GetMapping("/find-by-consumer-id")
    public List<AddressDto> findAllByConsumerId(@RequestParam int consumerId) {
        return service.findAllByConsumerId(consumerId);
    }

    @GetMapping("/find-by-provider-id")
    public List<AddressDto> findAllByProviderId(@RequestParam int providerId) {
        return service.findAllByProviderId(providerId);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        service.deleteById(id);
    }

    @PutMapping("/change-address/{id}")
    public void changeAddress(@PathVariable  int id, @RequestBody AddressDto addres) {
        service.changeAddress(id, addres);
    }
}