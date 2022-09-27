package br.com.contratei.service.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.repository.CommentRepository;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    @Lazy
    private CommentRepository repository;

    @Override
    public void createComment(CommentDto comment) {
        repository.save(new CommentEntity(comment));
    }

    @Override
    public List<CommentEntity> findAllByProviderId(int providerId) {
        return repository.findAllByProviderId(providerId);
    }
}
