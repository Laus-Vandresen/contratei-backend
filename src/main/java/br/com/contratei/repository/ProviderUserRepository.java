package br.com.contratei.repository;

import br.com.contratei.entity.ProviderUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderUserRepository extends JpaRepository<ProviderUserEntity, Integer> {

    List<ProviderUserEntity> findByEmail(String email);
}
