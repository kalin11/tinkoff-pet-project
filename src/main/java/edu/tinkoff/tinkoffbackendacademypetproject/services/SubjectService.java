package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.SubjectAlreadyExistsException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.SubjectCriteriaRepositoryImpl;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Сервис для работы с предметами
 */
@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectCriteriaRepositoryImpl subjectCriteriaRepository;

    /**
     * Получение всех предметов, которые есть на указанном курсе
     *
     * @param course курс
     * @return список предметов
     */
    @Transactional
    public List<Subject> findAllByCourseNumber(Long course) {
        if (isNull(course)) {
            return findAll();
        }
        return subjectCriteriaRepository.findAllByCourseNumber(course);
    }

    /**
     * Получение списка всех предметов
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
