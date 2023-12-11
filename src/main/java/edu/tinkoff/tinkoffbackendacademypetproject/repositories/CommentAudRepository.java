package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentAudEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentAudRepository extends JpaRepository<CommentAudEntity, Long> {
    Page<CommentAudEntity> findByComment_Id(Long comment_id, Pageable pageable);
}
