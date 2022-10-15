package br.com.contratei.service.impl;

import br.com.contratei.dto.ProposalDto;
import br.com.contratei.entity.ProposalEntity;
import br.com.contratei.repository.ProposalRepository;
import br.com.contratei.service.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalRepository repository;

    @Override
    public void save(ProposalDto proposalDto) {
        repository.save(new ProposalEntity(proposalDto));
    }

    @Override
    public void deleteProposal(int id) {
        repository.deleteById(id);
    }
}
