package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicRequestDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectTopicResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.TopicTypeResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Course;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.model.TopicType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


/**
 * Маппер для топика предмета
 */
@Mapper(componentModel = "spring")
public interface SubjectTopicMapper {
    /**
     * Получение названия предмета
     *
     * @param subject предмет
     * @return название предмета
     */
    @Named("getSubjectName")
    static SubjectResponseDTO getSubjectName(Subject subject) {
        return new SubjectResponseDTO(subject.getId(), subject.getName());
    }

    /**
     * Получение номера курса
     *
     * @param course курс
     * @return номер курса
     */
    @Named("getCourseNumber")
    static Long getCourseNumber(Course course) {
        return course.getCourseNumber();
    }

    /**
     * Получение ДТО типа топика
     *
     * @param type тип топика
     * @return ДТО типа топика
     */
    @Named("getTopicType")
    static TopicTypeResponseDTO getTopicType(TopicType type) {
        return new TopicTypeResponseDTO(type.getId(), type.getTopic().getDescription());
    }

    @Named("getCourseWithId")
    static Course getCourseWithId(Long id) {
        Course course = new Course();
        course.setCourseNumber(id);
        return course;
    }

    @Named("getSubjectWithId")
    static Subject getSubjectWithId(Long id) {
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }

    @Named("getTopicWithId")
    static TopicType getTopicWithId(Long id) {
        TopicType type = new TopicType();
        type.setId(id);
        return type;
    }

    /**
     * Конвертация топика предмета в ДТО
     *
     * @param subjectTopic топик предмета
     * @return ДТО топика предмета
     */
    @Mapping(source = "subjectTopic.subject", target = "subjectResponseDTO", qualifiedByName = "getSubjectName")
    @Mapping(source = "subjectTopic.course", target = "courseNumber", qualifiedByName = "getCourseNumber")
    @Mapping(source = "subjectTopic.type", target = "topicTypeResponseDTO", qualifiedByName = "getTopicType")
    SubjectTopicResponseDTO getSubjectTopicResponseDTO(SubjectTopic subjectTopic);

    /**
     * Конвертация списка топиков предметов в ДТО
     *
     * @param subjectTopic список топиков предметов
     * @return список ДТО топиков предметов
     */
    List<SubjectTopicResponseDTO> getListSubjectTopicResponseDTO(List<SubjectTopic> subjectTopic);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "dto.courseNumber", target = "course", qualifiedByName = "getCourseWithId")
    @Mapping(source = "dto.subjectId", target = "subject", qualifiedByName = "getSubjectWithId")
    @Mapping(source = "dto.topicId", target = "type", qualifiedByName = "getTopicWithId")
    SubjectTopic getSubjectTopicFromDTO(SubjectTopicRequestDTO dto);
}
