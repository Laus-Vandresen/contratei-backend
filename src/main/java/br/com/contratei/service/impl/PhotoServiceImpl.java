package br.com.contratei.service.impl;

import br.com.contratei.dto.PhotoDto;
import br.com.contratei.entity.PhotoEntity;
import br.com.contratei.repository.PhotoRepository;
import br.com.contratei.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository repository;

    @Override
    public List<PhotoDto> findPhotosProvider(int providerId) {
        return repository.findAllByProviderId(providerId).stream().map(PhotoDto::new).collect(Collectors.toList());
    }

    @Override
    public void deleteAllByProvider(int providerId) {
        repository.deleteAllByProviderId(providerId);
    }

    @Override
    public void saveAll(List<PhotoEntity> photos) {
        repository.saveAll(photos);
    }
}
