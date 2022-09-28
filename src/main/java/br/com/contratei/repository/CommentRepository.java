package br.com.contratei.repository;

import br.com.contratei.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>, CustomCommentRepository {

    List<CommentEntity> findAllByProviderId(int providerId);
}
