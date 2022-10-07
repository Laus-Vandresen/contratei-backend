package br.com.contratei.repository;

import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.enuns.ServiceTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderUserRepository extends JpaRepository<ProviderUserEntity, Integer>, CustomProviderUserRepository {

    List<ProviderUserEntity> findByEmail(String email);

    List<ProviderUserEntity> findByFirstNameStartsWith(String prefix);

    List<ProviderUserEntity> findByServiceType(ServiceTypeEnum serviceType);
}
