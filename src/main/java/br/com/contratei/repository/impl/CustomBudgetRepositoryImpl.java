package br.com.contratei.repository.impl;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.entity.*;
import br.com.contratei.enuns.ActingRegionEnum;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
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
import java.util.stream.Collectors;

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

        predicate.and(consumerUserEntity.id.eq(consumerId));

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

    @Override
    public Page<BudgetDto> findByProvider(Pageable page, int providerId, BudgetStatusEnum status) {
        final QBudgetEntity budgetEntity = QBudgetEntity.budgetEntity;
        final QProviderUserEntity providerUserEntity = QProviderUserEntity.providerUserEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        if (Objects.nonNull(status)) {
            predicate.and(budgetEntity.status.eq(status));
        }

        predicate.and(providerUserEntity.id.eq(providerId));

        JPAQuery<BudgetDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(BudgetDto.class,
                        budgetEntity))
                .from(budgetEntity)
                .join(budgetEntity.provider, providerUserEntity)
                .where(predicate)
                .orderBy(budgetEntity.id.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }

    @Override
    public Page<BudgetDto> findOpenBudgets(Pageable page, ProviderUserEntity providerUser, PriorityLevelEnum priorityLevel) {
        final QBudgetEntity budgetEntity = QBudgetEntity.budgetEntity;
        final QConsumerUserEntity consumerUserEntity = QConsumerUserEntity.consumerUserEntity;
        final QAddressEntity addressEntity = QAddressEntity.addressEntity;

        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(budgetEntity.status.eq(BudgetStatusEnum.OPEN));
        predicate.and(budgetEntity.provider.isNull());

        if (Objects.nonNull(providerUser)) {
            if (Objects.nonNull(providerUser.getServiceType())) {
                predicate.and(budgetEntity.serviceType.eq(providerUser.getServiceType()));
            }
            var mainAddressProvider = providerUser.getAddress().stream().filter(address -> address.getMain().equals(Boolean.TRUE)).collect(Collectors.toList()).get(0);
            if (providerUser.getActingRegion().equals(ActingRegionEnum.DISTRICT)) {
                predicate.and(addressEntity.district.eq(mainAddressProvider.getDistrict()));
            } else if (providerUser.getActingRegion().equals(ActingRegionEnum.CITY)) {
                predicate.and(addressEntity.city.eq(mainAddressProvider.getCity()));
            } else if (providerUser.getActingRegion().equals(ActingRegionEnum.STATE)) {
                predicate.and(addressEntity.state.eq(mainAddressProvider.getState()));
            }
        }

        if (Objects.nonNull(priorityLevel)) {
            predicate.and(budgetEntity.priority.eq(priorityLevel));
        }

        JPAQuery<BudgetDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(BudgetDto.class,
                        budgetEntity))
                .from(budgetEntity)
                .join(budgetEntity.consumer, consumerUserEntity)
                .join(consumerUserEntity.address, addressEntity)
                .where(predicate)
                .orderBy(budgetEntity.id.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
