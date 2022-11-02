package br.com.contratei.repository;

import br.com.contratei.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Integer> {

    List<CreditCardEntity> findAllByConsumerId(int consumerId);
}
