package br.com.contratei.controller;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @GetMapping
    public BudgetEntity findById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping("/find-by-consumer")
    public Page<BudgetDto> findByConsumer(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam int consumerId,
                                          @RequestParam BudgetStatusEnum status) {
        return service.findByConsumer(page, size, consumerId, status);
    }

    @PostMapping
    public BudgetDto save(@RequestBody BudgetDto dto) {
        return new BudgetDto(service.save(dto));
    }

}
