package br.com.contratei.service.impl;

import br.com.contratei.dto.AddressDto;
import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.PhotoDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.entity.CommentEntity;
import br.com.contratei.entity.PhotoEntity;
import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.repository.ProviderUserRepository;
import br.com.contratei.service.AddressService;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.PhotoService;
import br.com.contratei.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Autowired
    private PhotoService photoService;

    @Autowired
    private AddressService addressService;

    @Override
    public List<ProviderUserDto> findByName(String prefix) {
        return repository.findByFirstNameStartsWith(prefix).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public List<ProviderUserDto> findByServiceType(ServiceTypeEnum serviceType) {
        return repository.findByServiceType(serviceType).stream().map(ProviderUserDto::new).collect(Collectors.toList());
    }

    @Override
    public ProviderUserDto findById(int id) {
        return new ProviderUserDto(repository.findById(id).orElse(null));
    }

    @Override
    public Page<ProviderUserDto> findPageable(int page, int size, ServiceTypeEnum serviceType, Integer consumerId) {
        if (Objects.nonNull(consumerId)) {
            var consumerAddres = addressService.findMainByConsumerId(consumerId);
            return repository.findPageable(PageRequest.of(page, size), serviceType, consumerAddres);
        } else {
            return repository.findPageable(PageRequest.of(page, size), serviceType, null);
        }
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

    @Override
    public ProviderUserDto changeProviderUser(int providerId, CoreProviderDto coreProvider) {
        Optional<ProviderUserEntity> entity = repository.findById(providerId);
        if (entity.isPresent()) {
            entity.get().changeCoreData(coreProvider);
            return new ProviderUserDto(repository.save(entity.get()));
        }
        return null;
    }

    @Override
    @Transactional
    public void changeProviderPhotos(int providerId, List<PhotoDto> photos) {
        var photosEntity = photos.stream().map(PhotoEntity::new).collect(Collectors.toList());
        photoService.deleteAllByProvider(providerId);
        photoService.saveAll(photosEntity);
    }

    @Override
    public List<PhotoDto> findPhotosProvider(int providerId) {
        return photoService.findPhotosProvider(providerId);
    }
}
