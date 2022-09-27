package br.com.contratei.service.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ConsumerService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private CommentService commentService;

    @Autowired
    @Lazy
    private ProviderService providerService;

    @Override
    @Transactional
    public void createComment(CommentDto comment) {
        commentService.createComment(comment);
        providerService.recalculateScore(comment.getProvider().getId());
    }
}
