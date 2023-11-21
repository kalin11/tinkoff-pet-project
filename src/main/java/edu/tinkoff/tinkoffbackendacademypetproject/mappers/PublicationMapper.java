package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreatePublicationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationTitleAndIdResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Publication;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {FileMapper.class})
public interface PublicationMapper {
    @Named("emptySubjectTopicWithId")
    static SubjectTopic emptySubjectTopicWithId(Long id) {
        var subjectTopic = new SubjectTopic();
        subjectTopic.setId(id);
        return subjectTopic;
    }

    PublicationResponseDto toPublicationResponseDto(Publication publication);

    PublicationTitleAndIdResponseDto toPublicationTitleAndIdResponseDto(Publication publication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "subjectTopic", qualifiedByName = "emptySubjectTopicWithId", source = "subjectTopicId")
    Publication fromCreatePublicationRequestDto(CreatePublicationRequestDto publication);

}