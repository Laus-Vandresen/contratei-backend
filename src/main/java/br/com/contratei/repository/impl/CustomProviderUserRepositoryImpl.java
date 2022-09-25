package br.com.contratei.repository.impl;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.entity.QProviderUserEntity;
import br.com.contratei.enuns.ServiceTypeEnum;
import br.com.contratei.repository.CustomProviderUserRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class CustomProviderUserRepositoryImpl implements CustomProviderUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<ProviderUserDto> findPageable(Pageable page, ServiceTypeEnum serviceType) {
        final QProviderUserEntity providerUserEntity = QProviderUserEntity.providerUserEntity;

        BooleanBuilder where = new BooleanBuilder();

        if (Objects.nonNull(serviceType)) {
            where.and(providerUserEntity.serviceType.eq(serviceType));
        }

        JPAQuery<ProviderUserDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(ProviderUserDto.class,
                        providerUserEntity))
                .from(providerUserEntity)
                .where(where)
                .orderBy(providerUserEntity.id.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
