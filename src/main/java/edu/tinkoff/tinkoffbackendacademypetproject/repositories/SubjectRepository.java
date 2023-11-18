package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с Subject-объектами
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    /**
     * Проверка существования предмета с таким названием
     * @param name название предмета
     * @return факт существования
     */
    boolean existsSubjectByName(String name);
}
