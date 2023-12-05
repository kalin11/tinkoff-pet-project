package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.ChangeCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreateCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.CommentResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.CommentEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.model.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Named("emptyPublicationWithId")
    static PublicationEntity emptyPublicationWithId(Long id) {
        var publication = new PublicationEntity();
        publication.setId(id);
        return publication;
    }

    @Mapping(target = "nickname", expression = "java(comment.getIsAnonymous() ? \"\" : comment.getAccount().getNickname())")
    CommentResponseDto toCommentResponseDto(CommentEntity comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "publication", qualifiedByName = "emptyPublicationWithId", source = "publicationId")
    @Mapping(target = "account", ignore = true)
    CommentEntity fromCreateCommentRequestDto(CreateCommentRequestDto comment);

    @Mapping(target = "isAnonymous", ignore = true)
    @Mapping(target = "publication", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "account", ignore = true)
    CommentEntity fromChangeCommentRequestDto(ChangeCommentRequestDto comment);
}
