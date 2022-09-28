package br.com.contratei.repository.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.entity.QCommentEntity;
import br.com.contratei.entity.QProviderUserEntity;
import br.com.contratei.repository.CustomCommentRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<CommentDto> findByProviderId(Pageable page, int providerId) {
        final QCommentEntity commentEntity = QCommentEntity.commentEntity;
        final QProviderUserEntity providerUserEntity = QProviderUserEntity.providerUserEntity;

        JPAQuery<CommentDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(CommentDto.class,
                        commentEntity))
                .from(commentEntity)
                .join(commentEntity.provider, providerUserEntity)
                .where(providerUserEntity.id.eq(providerId))
                .orderBy(commentEntity.score.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
