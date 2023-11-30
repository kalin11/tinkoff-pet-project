package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreatePublicationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationTitleAndIdResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.PublicationEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopicEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {FileMapper.class})
public interface PublicationMapper {
    @Named("emptySubjectTopicWithId")
    static SubjectTopicEntity emptySubjectTopicWithId(Long id) {
        var subjectTopic = new SubjectTopicEntity();
        subjectTopic.setId(id);
        return subjectTopic;
    }

    @Mapping(target = "fullName", source = "account.fullName")
    PublicationResponseDto toPublicationResponseDto(PublicationEntity publication);

    @Mapping(target = "fullName", source = "account.fullName")
    PublicationTitleAndIdResponseDto toPublicationTitleAndIdResponseDto(PublicationEntity publication);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "subjectTopic", qualifiedByName = "emptySubjectTopicWithId", source = "subjectTopicId")
    @Mapping(target = "account", ignore = true)
    PublicationEntity fromCreatePublicationRequestDto(CreatePublicationRequestDto publication);

}