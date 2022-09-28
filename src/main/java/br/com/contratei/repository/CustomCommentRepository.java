package br.com.contratei.repository;

import br.com.contratei.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomCommentRepository {

    Page<CommentDto> findByProviderId(Pageable page, int providerId);
}
