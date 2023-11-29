package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectTopicResponseDto;
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
@Mapper(componentModel = "spring", uses = {SubjectMapper.class, TopicMapper.class})
public interface SubjectTopicMapper {
    @Named("getSubjectWithId")
    static Subject getSubjectWithId(Long id) {
        var subject = new Subject();
        subject.setId(id);
        return subject;
    }

    @Named("getTopicWithId")
    static TopicType getTopicWithId(Long id) {
        var type = new TopicType();
        type.setId(id);
        return type;
    }

    /**
     * Конвертация топика предмета в ДТО
     *
     * @param subjectTopic топик предмета
     * @return ДТО топика предмета
     */
    @Mapping(target = "topicTypeResponseDTO", source = "type")
    @Mapping(target = "subjectResponseDTO", source = "subject")
    SubjectTopicResponseDto getSubjectTopicResponseDTO(SubjectTopic subjectTopic);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "subjectId", target = "subject", qualifiedByName = "getSubjectWithId")
    @Mapping(source = "topicId", target = "type", qualifiedByName = "getTopicWithId")
    @Mapping(target = "publications", ignore = true)
    SubjectTopic getSubjectTopicFromDTO(SubjectTopicRequestDto dto);
}
