package br.com.contratei.service;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.CommentEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    void createComment(CommentDto comment);

    List<CommentEntity> findAllByProviderId(int providerId);

    Page<CommentDto> findByProviderId(int page, int size, int providerId);
}
