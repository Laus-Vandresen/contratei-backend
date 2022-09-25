package br.com.contratei.service;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    void createComment(CommentDto comment);

    List<CommentEntity> findAllByProviderId(int providerId);
}
