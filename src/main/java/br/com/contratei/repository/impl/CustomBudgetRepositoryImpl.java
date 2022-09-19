package br.com.contratei.repository.impl;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.QBudgetEntity;
import br.com.contratei.entity.QConsumerUserEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.repository.CustomBudgetRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Objects;

public class CustomBudgetRepositoryImpl implements CustomBudgetRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<BudgetDto> findByConsumer(Pageable page, int consumerId, BudgetStatusEnum status) {
        final QBudgetEntity budgetEntity = QBudgetEntity.budgetEntity;
        final QConsumerUserEntity consumerUserEntity = QConsumerUserEntity.consumerUserEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        if (Objects.nonNull(status)) {
            predicate.and(budgetEntity.status.eq(status));
        }

        JPAQuery<BudgetDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(BudgetDto.class,
                        budgetEntity))
                .from(budgetEntity)
                .join(budgetEntity.consumer, consumerUserEntity)
                .where(predicate)
                .orderBy(budgetEntity.id.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
