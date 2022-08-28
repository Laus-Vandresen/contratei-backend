package br.com.contratei.repository.impl;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.entity.QBugetEntity;
import br.com.contratei.entity.QConsumerUserEntity;
import br.com.contratei.repository.CustomBugetRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomBugetRepositoryImpl implements CustomBugetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<BugetDto> findByConsumer(Pageable page, int consumerId) {
        final QBugetEntity bugetEntity = QBugetEntity.bugetEntity;
        final QConsumerUserEntity consumerUserEntity = QConsumerUserEntity.consumerUserEntity;

        JPAQuery<BugetDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(BugetDto.class,
                        bugetEntity))
                .from(bugetEntity)
                .join(bugetEntity.consumer, consumerUserEntity)
                .orderBy(bugetEntity.openingDate.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
