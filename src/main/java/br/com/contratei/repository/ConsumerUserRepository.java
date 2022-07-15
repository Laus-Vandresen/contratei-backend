package br.com.contratei.repository;

import br.com.contratei.entity.ConsumerUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumerUserRepository extends JpaRepository<ConsumerUserEntity, Integer> {

    List<ConsumerUserEntity> findByEmail(String email);
}
