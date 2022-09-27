package br.com.contratei.controller;

import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    @Autowired
    private ProviderService service;

    @GetMapping("/name")
    public List<ProviderUserDto> findByName(@RequestParam String prefix) {
        return service.findByName(prefix);
    }

    @GetMapping("/service-type")
    public List<ProviderUserDto> findByServiceType(@RequestParam ServiceTypeEnum serviceType) {
        return service.findByServiceType(serviceType);
    }

    @GetMapping("/find-by-id")
    public ProviderUserDto findById(@RequestParam int id) {
        return service.findById(id);
    }

    @GetMapping()
    public Page<ProviderUserDto> find(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) ServiceTypeEnum serviceType) {
        return service.findPageable(page, size, serviceType);
    }

    @PutMapping
    public ProviderUserDto changeProviderUser(@PathVariable  int providerId, @RequestBody CoreProviderDto coreProvider) {
        return service.changeProviderUser(providerId, coreProvider);
    }
}
