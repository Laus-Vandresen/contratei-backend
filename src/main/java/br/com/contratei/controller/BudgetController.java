package br.com.contratei.controller;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
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
    public BudgetDto findById(@RequestParam Integer id) {
        return service.findById(id);
    }

    @GetMapping("/find-by-consumer")
    public Page<BudgetDto> findByConsumer(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam int consumerId,
                                          @RequestParam(name = "status", required = false) BudgetStatusEnum status) {
        return service.findByConsumer(page, size, consumerId, status);
    }

    @GetMapping("/find-by-provider")
    public Page<BudgetDto> findByProvider(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam int providerId,
                                          @RequestParam(name = "status", required = false) BudgetStatusEnum status) {
        return service.findByProvider(page, size, providerId, status);
    }

    @GetMapping("/find-open-budgets")
    public Page<BudgetDto> findOpenBudgets(@RequestParam int page,
                                           @RequestParam int size,
                                           @RequestParam int providerId,
                                           @RequestParam(required = false) PriorityLevelEnum priorityLevel) {
        return service.findOpenBudgets(page, size, providerId, priorityLevel);
    }

    @PostMapping
    public BudgetDto save(@RequestBody BudgetDto dto) {
        return new BudgetDto(service.save(dto));
    }

    @GetMapping("/exist-budget")
    public Boolean checkExistenceBudget(@RequestParam int consumerId, @RequestParam int providerId) {
        return service.checkExistenceBudget(consumerId, providerId);
    }

    @PutMapping("/{budgetId}")
    public BudgetDto changeBudget(@PathVariable  int budgetId, @RequestBody BudgetDto budget) {
        return service.changeBudget(budgetId, budget);
    }

}
