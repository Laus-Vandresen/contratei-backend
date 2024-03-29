package br.com.contratei.repository;

import br.com.contratei.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

    List<AddressEntity> findAllByConsumerId(int consumerId);

    List<AddressEntity> findAllByProviderId(int providerId);

    List<AddressEntity> findAllByConsumerIdAndMainIsTrue(int consumerId);

    AddressEntity findByProviderIdAndMainIsTrue(int providerId);
}
