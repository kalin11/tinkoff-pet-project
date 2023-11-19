package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

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
}
