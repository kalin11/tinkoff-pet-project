package edu.tinkoff.tinkoffbackendacademypetproject.repositories;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Course;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.model.TopicType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectTopicRepository extends JpaRepository<SubjectTopic, Long> {
    boolean existsByCourseAndSubjectAndType(Course course, Subject subject, TopicType type);
}
