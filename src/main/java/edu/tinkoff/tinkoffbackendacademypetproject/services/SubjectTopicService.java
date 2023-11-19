package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.SubjectTopicCriteriaRepository;
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

    /**
     * Получение всех топиков по заданному курсу и названию
     * @param courseNumber номер курса
     * @param name название предмета
     * @return список всех топиков
     */
    @Transactional
    public List<SubjectTopic> findAllByCourseNumberAndSubjectName(Long courseNumber, String name) {
        return criteriaRepository.findAllByCourseNumberAndSubjectName(courseNumber, name.toUpperCase());
    }
}
