package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с Subject-объектами
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    /**
     * Проверка существования предмета с таким названием
     *
     * @param name название предмета
     * @return факт существования
     */
    boolean existsSubjectByName(String name);

    Page<Subject> findDistinctBySubjectTopics_Course_CourseNumber(Long courseNumber, Pageable pageable);
}
