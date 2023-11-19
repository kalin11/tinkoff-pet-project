package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import com.querydsl.jpa.impl.JPAQueryFactory;
import edu.tinkoff.tinkoffbackendacademypetproject.model.QSubject;
import edu.tinkoff.tinkoffbackendacademypetproject.model.QSubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с предметами
 */
@Repository
@RequiredArgsConstructor
public class SubjectCriteriaRepositoryImpl implements SubjectCriteriaRepository {
    private final EntityManager entityManager;

    /**
     * Получение списка предметов по указанному курсу
     *
     * @param courseNumber номер курса
     * @return список предметов на заданном курсе
     */
    @Override
    public List<Subject> findAllByCourseNumber(Long courseNumber) {
        var query = new JPAQueryFactory(entityManager);
        var subjectTopic = QSubjectTopic.subjectTopic;
        var subject = QSubject.subject;

        return query
                .select(subject)
                .from(subjectTopic)
                .join(subject).on(subjectTopic.subject.id.eq(subject.id))
                .where(subjectTopic.course.courseNumber.eq(courseNumber))
                .orderBy(subject.id.asc())
                .fetch();
    }

}
