package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.tinkoff.tinkoffbackendacademypetproject.model.QSubject;
import edu.tinkoff.tinkoffbackendacademypetproject.model.QSubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Репозиторий для работы с топиками предметов
 */
@Repository
@RequiredArgsConstructor
public class SubjectTopicCriteriaRepositoryImpl implements SubjectTopicCriteriaRepository {
    private final EntityManager entityManager;

    /**
     * Получение типов топиков по указанному номеру курса и его имени
     *
     * @param courseNumber номер курса
     * @param name         название предмета
     * @return список подходящих топиков предметов
     */
    @Override
    public List<SubjectTopic> findAllByCourseNumberAndSubjectId(Long courseNumber, Long subjectId) {
        var query = new JPAQueryFactory(entityManager);
        var subjectTopic = QSubjectTopic.subjectTopic;
        var subject = QSubject.subject;

        return query
                .select(subjectTopic)
                .from(subjectTopic)
                .join(subject).on(subjectTopic.subject.id.eq(subject.id))
                .where(getPredicateByCourseNumberAndSubjectName(subjectTopic, courseNumber, subjectId))
                .orderBy(subject.id.asc())
                .fetch();
    }

    /**
     * Получение предиката для фильтрации
     *
     * @param subjectTopic объект топика предмета
     * @param courseNumber номер курса
     * @param subjectId    id предмета
     * @return предикат
     */
    private Predicate getPredicateByCourseNumberAndSubjectName(QSubjectTopic subjectTopic, Long courseNumber, Long subjectId) {
        List<Predicate> predicates = new ArrayList<>();
        if (nonNull(courseNumber)) {
            predicates.add(subjectTopic.course.courseNumber.eq(courseNumber));
        }
        if (nonNull(subjectId)) {
            predicates.add(subjectTopic.subject.id.eq(subjectId));
        }
        return predicates.isEmpty() ? subjectTopic.isNotNull() : ExpressionUtils.allOf(predicates);
    }
}
