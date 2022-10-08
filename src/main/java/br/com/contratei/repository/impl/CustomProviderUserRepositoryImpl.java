package br.com.contratei.repository.impl;

import br.com.contratei.dto.AddressDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.entity.QAddressEntity;
import br.com.contratei.entity.QProviderUserEntity;
import br.com.contratei.enuns.ActingRegionEnum;
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
    public Page<ProviderUserDto> findPageable(Pageable page, ServiceTypeEnum serviceType, AddressDto consumerAddres) {
        final QProviderUserEntity providerUserEntity = QProviderUserEntity.providerUserEntity;
        final QAddressEntity addressEntity = QAddressEntity.addressEntity;

        BooleanBuilder where = new BooleanBuilder();

        if (Objects.nonNull(serviceType)) {
            where.and(providerUserEntity.serviceType.eq(serviceType));
        }
        if (Objects.nonNull(consumerAddres)) {
            where.and(addressEntity.main.isTrue());
            where.and((providerUserEntity.actingRegion.eq(ActingRegionEnum.DISTRICT).and(addressEntity.district.eq(consumerAddres.getDistrict())))
                    .or((providerUserEntity.actingRegion.eq(ActingRegionEnum.CITY).and(addressEntity.city.eq(consumerAddres.getCity()))))
                    .or((providerUserEntity.actingRegion.eq(ActingRegionEnum.STATE).and(addressEntity.state.eq(consumerAddres.getState()))))
                    .or((providerUserEntity.actingRegion.eq(ActingRegionEnum.COUNTRY)))
            );
        }

        JPAQuery<ProviderUserDto> query = new JPAQuery<>(em);
        query.select(Projections.constructor(ProviderUserDto.class,
                        providerUserEntity))
                .from(providerUserEntity)
                .join(providerUserEntity.address, addressEntity)
                .where(where)
                .orderBy(providerUserEntity.score.desc());

        query.limit(page.getPageSize());
        query.offset(page.getOffset());

        return new PageImpl<>(query.fetch(), page, query.fetchCount());
    }
}
