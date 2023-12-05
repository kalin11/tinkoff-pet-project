package edu.tinkoff.tinkoffbackendacademypetproject.service;

import edu.tinkoff.tinkoffbackendacademypetproject.config.PostgresTestConfig;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.*;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.*;
import edu.tinkoff.tinkoffbackendacademypetproject.services.PublicationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(initializers = PostgresTestConfig.Initializer.class)
class PublicationEntityServiceTest {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SubjectTopicRepository subjectTopicRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    @AfterEach
    void clear() {
        subjectTopicRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE subject_topic_pk_seq RESTART");
        subjectRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE subject_pk_seq RESTART");
        publicationRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE publication_pk_seq RESTART");
        accountRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE account_pk_seq RESTART");
    }

    @DisplayName("Find all publications in one category")
    @ParameterizedTest(name = "{index} - find all publications by subjectTopic id {2}")
    @CsvSource(value = {
            "0, 50, 1",
    })
    void getPublicationsInOneCategoryTest(Integer pageNumber, Integer pageSize, Long subjectTopicId) {
        // given
        createBeforeStart();
        createPublications(subjectTopicId);
        // when
        var publicationsWhen = publicationService.getPublicationsInOneCategory(pageNumber, pageSize, subjectTopicId);

        // then
        var publications = publicationRepository.findBySubjectTopic_Id(subjectTopicId, PageRequest.of(pageNumber, pageSize, Sort.by("id")));
        assertEquals(publicationsWhen.getSize(), publications.getSize());
        for (int i = 0; i < publications.getContent().size(); i++) {
            assertEquals(publications.getContent().get(i).getId(), publicationsWhen.getContent().get(i).getId());
        }
    }

    @DisplayName("Get publication")
    @ParameterizedTest(name = "{index} - publication {0} is find")
    @ValueSource(longs = {1, 2})
    void getPublicationTest(Long id) throws EntityModelNotFoundException {
        // given
        createBeforeStart();
        createPublications(1L);

        // when
        var publicationWhen = publicationService.getPublication(id);

        // then
        var publication = publicationRepository.findById(id);
        assertTrue(publication.isPresent());
        var publicationGet = publication.get();
        assertEquals(publicationWhen.getId(), publicationGet.getId());
        assertEquals(publicationWhen.getTitle(), publicationGet.getTitle());
        assertEquals(publicationWhen.getDescription(), publicationGet.getDescription());
    }

    @DisplayName("Get publication with throw")
    @ParameterizedTest(name = "{index} - publication {0} is not find")
    @ValueSource(longs = {1, 2, 3})
    void getPublicationWithThrowTest(Long id) {
        // given

        // when
        var exception = assertThrows(
                EntityModelNotFoundException.class,
                () -> publicationService.getPublication(id)
        );

        // then
        assertEquals("Публикации с id: " + id + " не найдено", exception.getMessage());
    }

    private void createBeforeStart() {
        subjectRepository.save(new SubjectEntity(null, "Mathematics", null, courseRepository.findById(1).get()));
        subjectTopicRepository.save(new SubjectTopicEntity(null, topicRepository.findById(1L).get(), subjectRepository.findById(1L).get(), null));
        accountRepository.save(new Account(null, "dan@dam.ru", "1234", "Daniil K", null, null, null, null, null, false, Role.ROLE_USER, null, null, null));
    }

    private void createPublications(Long subjectTopicId) {
        publicationRepository.save(new PublicationEntity(null, "Первая публикация", "First", null, subjectTopicRepository.findById(subjectTopicId).get(), null, null, accountRepository.findById(1L).get()));
        publicationRepository.save(new PublicationEntity(null, "Вторая публикация", "Second", null, subjectTopicRepository.findById(subjectTopicId).get(), null, null, accountRepository.findById(1L).get()));
        publicationRepository.save(new PublicationEntity(null, "Третья публикация", "Third", null, subjectTopicRepository.findById(subjectTopicId).get(), null, null, accountRepository.findById(1L).get()));
    }
}

