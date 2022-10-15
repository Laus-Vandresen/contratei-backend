package br.com.contratei.controller;

import br.com.contratei.dto.ProposalDto;
import br.com.contratei.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proposal")
public class ProposalController {

    @Autowired
    private ProposalService service;

    @PostMapping()
    public void createProposal(@RequestBody ProposalDto proposalDto) {
        service.save(proposalDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProposal(@PathVariable int id) {
        service.deleteProposal(id);
    }
}
