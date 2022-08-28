package br.com.contratei.repository;

import br.com.contratei.entity.BugetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugetRepository extends JpaRepository<BugetEntity, Integer>, CustomBugetRepository {
}
