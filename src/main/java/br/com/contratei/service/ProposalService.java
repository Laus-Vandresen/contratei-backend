package br.com.contratei.service;

import br.com.contratei.dto.ProposalDto;

import java.util.List;

public interface ProposalService {

    void save(ProposalDto proposalDto);

    ProposalDto findById(int id);

    List<ProposalDto> findAllByBudget(int budgetId);

    void acceptProposal(int proposalId, int budgetId);
}
