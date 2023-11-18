package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с предметом
 */
@Repository
public interface SubjectCriteriaRepository {
    List<Subject> findAllByCourseNumber(Long courseNumber);
}
