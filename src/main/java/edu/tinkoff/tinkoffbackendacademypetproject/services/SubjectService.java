package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.SubjectAlreadyExistsException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с предметами
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class SubjectService {
    private final SubjectRepository subjectRepository;

    /**
     * Получение всех предметов, которые есть на указанном курсе
     *
     * @param courseNumber фильтр курса
     * @param pageSize     размер страницы
     * @param pageNumber   номер текущей страницы
     * @return список предметов
     */

    @Transactional
    public Page<Subject> findAllByCourseNumber(Integer pageNumber, Integer pageSize, Long courseNumber) {
        log.info("request with " + pageNumber + " " + pageSize);
        return subjectRepository.findDistinctBySubjectTopics_Course_CourseNumber(courseNumber, PageRequest.of(pageNumber, pageSize, Sort.by("id")));
    }

    /**
     * Получение списка всех предметов
     *
     * @return список всех предметов
     */
    @Transactional
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /**
     * Создание нового предмета
     *
     * @param subject предмет, который надо создать
     * @return сохраненный предмет
     * @throws SubjectAlreadyExistsException если предмет с таким названием уже существует
     */
    @Transactional
    public Subject createSubject(Subject subject) throws SubjectAlreadyExistsException {
        if (subjectRepository.existsSubjectByName(subject.getName())) {
            throw new SubjectAlreadyExistsException(subject.getName());
        }
        return subjectRepository.save(subject);
    }
}
