package br.com.contratei.controller;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.entity.BugetEntity;
import br.com.contratei.service.BugetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buget")
public class BugetController {

    @Autowired
    private BugetService service;

    @GetMapping
    public BugetEntity findById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping("/find-by-consumer")
    public Page<BugetDto> findByConsumer(@RequestParam int page,
                                         @RequestParam int size,
                                         @RequestParam int consumerId) {
        return service.findByConsumer(page, size, consumerId);
    }

    @PostMapping
    public BugetDto save(@RequestBody BugetDto dto) {
        return new BugetDto(service.save(dto));
    }

}
