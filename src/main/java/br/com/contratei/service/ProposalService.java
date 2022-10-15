package br.com.contratei.service;

import br.com.contratei.dto.ProposalDto;

public interface ProposalService {

    void save(ProposalDto proposalDto);

    void deleteProposal(int id);
}
