package br.com.contratei.service.impl;

import br.com.contratei.dto.ProposalDto;
import br.com.contratei.entity.ProposalEntity;
import br.com.contratei.repository.ProposalRepository;
import br.com.contratei.service.ProposalService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private ProviderService providerService;

    @Override
    public void save(ProposalDto proposalDto) {
        repository.save(new ProposalEntity(proposalDto));
    }

    @Override
    public ProposalDto findById(int id) {
        var proposalEntity = repository.findById(id);
        return proposalEntity.isPresent() ? new ProposalDto(proposalEntity.get()) : null;
    }

    @Override
    public List<ProposalDto> findAllByBudget(int budgetId) {
        var proposalEntityList = repository.findAllByBudgetId(budgetId);
        return proposalEntityList.stream().map(ProposalDto::new).collect(Collectors.toList());
    }

    @Override
    public void acceptProposal(int proposalId, int budgetId) {
        var proposal = repository.findById(proposalId);
        if (proposal.isPresent()) {
            proposal.get().acceptProposal();
            repository.save(proposal.get());
            var proposalsNotAccepted = repository.findAllByBudgetIdAndIdIsNot(budgetId, proposalId);
            proposalsNotAccepted.forEach(ProposalEntity::refuseProposal);
            repository.saveAll(proposalsNotAccepted);
        }
    }
}
