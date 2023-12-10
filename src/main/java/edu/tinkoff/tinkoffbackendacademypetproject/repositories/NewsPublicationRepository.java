package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.NewsPublicationEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.model.PublicationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPublicationRepository extends JpaRepository<NewsPublicationEntity, Long> {
}
