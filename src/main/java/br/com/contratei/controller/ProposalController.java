package br.com.contratei.controller;

import br.com.contratei.dto.ProposalDto;
import br.com.contratei.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @PostMapping()
    public void createProposal(@RequestBody ProposalDto proposalDto) {
        service.save(proposalDto);
    }

    @GetMapping()
    public ProposalDto findById(@RequestParam int id) {
        return service.findById(id);
    }

    @GetMapping("find-by-budget")
    public List<ProposalDto> findAllByBudget(@RequestParam int budgetId) {
        return service.findAllByBudget(budgetId);
    }

    @PostMapping("accept-proposal")
    public void acceptProposal(@RequestParam int proposalId, @RequestParam int budgetId) {
        service.acceptProposal(proposalId, budgetId);
    }

    @GetMapping("find-existing-proposal")
    public ProposalDto findExistingProposal(@RequestParam int providerId, @RequestParam int budgetId) {
        return service.findExistingProposal(providerId, budgetId);
    }
}
