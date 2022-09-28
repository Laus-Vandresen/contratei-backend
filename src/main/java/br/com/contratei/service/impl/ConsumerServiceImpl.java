package br.com.contratei.service.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.dto.ConsumerUserDto;
import br.com.contratei.dto.CoreConsumerDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.entity.ConsumerUserEntity;
import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.repository.ConsumerUserRepository;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ConsumerService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private CommentService commentService;

    @Autowired
    @Lazy
    private ProviderService providerService;

    @Autowired
    private ConsumerUserRepository repository;

    @Override
    @Transactional
    public void createComment(CommentDto comment) {
        commentService.createComment(comment);
        providerService.recalculateScore(comment.getProvider().getId());
    }

    @Override
    public ConsumerUserDto changeConsumerUser(int consumerId, CoreConsumerDto coreConsumer) {
        Optional<ConsumerUserEntity> entity = repository.findById(consumerId);
        if (entity.isPresent()) {
            entity.get().changeCoreData(coreConsumer);
            return new ConsumerUserDto(repository.save(entity.get()));
        }
        return null;
    }
}
