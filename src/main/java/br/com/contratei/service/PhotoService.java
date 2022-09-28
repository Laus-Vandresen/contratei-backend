package br.com.contratei.service;

import br.com.contratei.dto.PhotoDto;

import java.util.List;

public interface PhotoService {
    List<PhotoDto> findPhotosProvider(int providerId);
}
