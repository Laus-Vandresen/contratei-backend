package br.com.contratei.repository;

import br.com.contratei.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoEntity, Integer> {

    List<PhotoEntity> findAllByProviderId(int providerId);

    void deleteAllByProviderId(int providerId);
}
