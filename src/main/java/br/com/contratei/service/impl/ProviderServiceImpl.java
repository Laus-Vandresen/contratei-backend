package br.com.contratei.service.impl;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.repository.ProviderUserRepository;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    @Lazy
    private ProviderUserRepository repository;

    @Autowired
    @Lazy
    private CommentService commentService;

    @Override
    public List<ProviderUserDto> findByName(String prefix) {
        return repository.findByFirstNameStartsWithOrderByKmWorkRangeAsc(prefix).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType) {
        return repository.findByServiceTypeOrderByKmWorkRangeAsc(serviceType).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public ProviderUserDto findById(int id) {
        return new ProviderUserDto(repository.findById(id).orElse(null));
    }

    @Override
    public Page<ProviderUserDto> findPageable(int page, int size, ServiceTypeEnum serviceType) {
        return repository.findPageable(PageRequest.of(page, size), serviceType);
    }

    @Override
    public void recalculateScore(int providerId) {
        List<CommentEntity> commentEntities = commentService.findAllByProviderId(providerId);
        int qtdComments = commentEntities.size();
        AtomicReference<Double> score = new AtomicReference<>(0D);
        commentEntities.stream().forEach(comment -> {
            score.updateAndGet(v -> v + comment.getScore());
        });
        ProviderUserEntity provider = repository.findById(providerId).orElse(null);
        provider.updateScore(score.get() / qtdComments);
    }
}
