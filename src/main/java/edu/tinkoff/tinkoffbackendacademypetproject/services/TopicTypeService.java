package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.model.TopicType;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с типами топика
 */
@Service
@RequiredArgsConstructor
public class TopicTypeService {
    /**
     * Репозиторий для работы с типами топиков
     */
    private final TopicRepository topicRepository;

    /**
     * Получение всех типов топиков
     *
     * @return список типов топиков
     */
    public List<TopicType> findAll() {
        return topicRepository.findAll();
    }
}
