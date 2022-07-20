package br.com.contratei.controller;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.service.SearchProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search-provider")
public class SearchProviderController {

    @Autowired
    private SearchProviderService service;

    @GetMapping("/name")
    public List<ProviderUserDto> findByName(@RequestParam String prefix) {
        return service.findByName(prefix);
    }

    @GetMapping("/service-type")
    public List<ProviderUserDto> findByServiceType(@RequestParam ServiceTypeEnum serviceType) {
        return service.findByServiceType(serviceType);
    }
}
