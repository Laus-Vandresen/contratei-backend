package br.com.contratei.service.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.repository.CommentRepository;
import br.com.contratei.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<CommentDto> findByProviderId(int page, int size, int providerId) {
        return repository.findByProviderId(PageRequest.of(page, size), providerId);
    }
}
