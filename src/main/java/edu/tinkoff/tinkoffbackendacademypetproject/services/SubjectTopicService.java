package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.AlreadyExistsException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.SubjectTopicAlreadyExistsException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Course;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.model.TopicType;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с типами топиков для предметов
 */
@Service
@RequiredArgsConstructor
public class SubjectTopicService {
    /**
     * Репозиторий для работы с топиками для предметов
     */
    private final SubjectTopicCriteriaRepository criteriaRepository;
    private final SubjectTopicRepository subjectTopicRepository;
    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final SubjectRepository subjectRepository;

    /**
     * Получение всех топиков по заданному курсу и названию
     *
     * @param courseNumber номер курса
     * @param subjectId    название предмета
     * @return список всех топиков
     */
    @Transactional
    public List<SubjectTopic> findAllByCourseNumberAndSubjectId(Long courseNumber, Long subjectId) {
        return criteriaRepository.findAllByCourseNumberAndSubjectId(courseNumber, subjectId);
    }

    @Transactional
    public Course getCourseIfExists(Long id) throws EntityModelNotFoundException {
        return courseRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Курса", "id", id));
    }

    @Transactional
    public Subject getSubjectIfExists(Long id) throws EntityModelNotFoundException {
        return subjectRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Предмета", "id", id));
    }

    @Transactional
    public TopicType getTopicTypeIfExists(Long id) throws EntityModelNotFoundException {
        return topicRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Типа топика", "id", id));
    }

    @Transactional
    public SubjectTopic createSubjectTopic(SubjectTopic subjectTopic) throws EntityModelNotFoundException, AlreadyExistsException {
        Course course = getCourseIfExists(subjectTopic.getCourse().getCourseNumber());
        Subject subject = getSubjectIfExists(subjectTopic.getSubject().getId());
        TopicType topicType = getTopicTypeIfExists(subjectTopic.getType().getId());
        subjectTopic.setCourse(course);
        subjectTopic.setSubject(subject);
        subjectTopic.setType(topicType);

        if (subjectTopicRepository.existsByCourseAndSubjectAndType(course, subject, topicType)) {
            throw new SubjectTopicAlreadyExistsException();
        }
        return subjectTopicRepository.save(subjectTopic);
    }

    public SubjectTopic getSubjectTopic(Long id) throws EntityModelNotFoundException {
        return subjectTopicRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Топика", "id", id));
    }
}
