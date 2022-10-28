package br.com.contratei.service.impl;

import br.com.contratei.dto.ProposalDto;
import br.com.contratei.entity.ProposalEntity;
import br.com.contratei.repository.ProposalRepository;
import br.com.contratei.service.BudgetService;
import br.com.contratei.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepository repository;

    @Autowired
    private BudgetService budgetService;

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
    @Transactional
    public void acceptProposal(int proposalId, int budgetId) {
        var proposal = repository.findById(proposalId);
        if (proposal.isPresent()) {
            var budget = budgetService.findById(budgetId);
            proposal.get().acceptProposal();
            budget.acceptProposal(proposal.get());
            repository.save(proposal.get());
            budgetService.save(budget);
            denyOtherProposals(proposalId, budgetId);
        }
    }

    @Override
    public ProposalDto findExistingProposal(int providerId, int budgetId) {
        var proposal = repository.findByProviderIdAndBudgetId(providerId, budgetId);
        if (Objects.nonNull(proposal)) {
            return new ProposalDto(proposal);
        }
        return null;
    }

    private void denyOtherProposals(int proposalId, int budgetId) {
        var proposalsNotAccepted = repository.findAllByBudgetIdAndIdIsNot(budgetId, proposalId);
        proposalsNotAccepted.forEach(ProposalEntity::refuseProposal);
        repository.saveAll(proposalsNotAccepted);
    }
}
