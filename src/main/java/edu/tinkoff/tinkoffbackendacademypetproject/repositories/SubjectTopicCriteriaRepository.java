package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с топиками предметов
 */
@Repository
public interface SubjectTopicCriteriaRepository {
    /**
     * Получение типов топиков по указанному номеру курса и его имени
     *
     * @param courseNumber номер курса
     * @param subjectId         id предмета
     * @return список подходящих топиков предметов
     */
    List<SubjectTopic> findAllByCourseNumberAndSubjectId(Long courseNumber, Long subjectId);
}
