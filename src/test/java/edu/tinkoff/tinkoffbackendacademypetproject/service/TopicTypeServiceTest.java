package edu.tinkoff.tinkoffbackendacademypetproject.service;

import edu.tinkoff.tinkoffbackendacademypetproject.config.PostgresTestConfig;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.TopicRepository;
import edu.tinkoff.tinkoffbackendacademypetproject.services.TopicTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(initializers = PostgresTestConfig.Initializer.class)
public class TopicTypeServiceTest {
    @Autowired
    private TopicTypeService topicTypeService;

    @Autowired
    private TopicRepository topicRepository;

    @DisplayName("Find all topic")
    @Test
    public void findAllTopicTest() {
        // given

        // when
        var topicsWhen = topicTypeService.findAll();

        // then
        var topics = topicRepository.findAll();
        for (int i = 0; i < 3; i++) {
            assertEquals(topics.get(i).getId(), topicsWhen.get(i).getId());
            assertEquals(topics.get(i).getTopic(), topicsWhen.get(i).getTopic());
        }
    }

    @DisplayName("Get topic")
    @ParameterizedTest(name = "{index} - topic {0} is find")
    @ValueSource(longs = {1, 2, 3})
    public void getTopicTest(Long id) throws EntityModelNotFoundException {
        // given

        // when
        var topicWhen = topicTypeService.getTopicType(id);

        // then
        var topic = topicRepository.findById(id);
        assertTrue(topic.isPresent());
        assertEquals(topic.get().getId(), topicWhen.getId());
        assertEquals(topic.get().getTopic(), topicWhen.getTopic());
    }

    @DisplayName("Get topic with throw")
    @ParameterizedTest(name = "{index} - topic {0} is not find")
    @ValueSource(longs = {4, 5, 6})
    public void getTopicWithThrowTest(Long id) {
        // given

        // when
        var exception = assertThrows(
                EntityModelNotFoundException.class,
                () -> topicTypeService.getTopicType(id)
        );

        // then
        assertEquals("Типа топика с id: " + id + " не найдено", exception.getMessage());
    }
}
