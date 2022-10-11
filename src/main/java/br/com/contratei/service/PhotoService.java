package br.com.contratei.service;

import br.com.contratei.dto.PhotoDto;
import br.com.contratei.entity.PhotoEntity;

import java.util.List;

public interface PhotoService {

    List<PhotoDto> findPhotosProvider(int providerId);

    void deleteAllByProvider(int providerId);

    void saveAll(List<PhotoEntity> photos);
}
