package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.ChangeCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreateCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.CommentResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Comment;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Publication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Named("emptyPublicationWithId")
    static Publication emptyPublicationWithId(Long id) {
        var publication = new Publication();
        publication.setId(id);
        return publication;
    }

    CommentResponseDto toCommentResponseDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "publication", qualifiedByName = "emptyPublicationWithId", source = "publicationId")
    Comment fromCreateCommentRequestDto(CreateCommentRequestDto comment);

    @Mapping(target = "publication", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment fromChangeCommentRequestDto(ChangeCommentRequestDto comment);
}
