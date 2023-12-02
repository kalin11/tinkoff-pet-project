package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.ChangeCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CommentsOnThePublicationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreateCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.CommentResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PageResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.CommentMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PageMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsAdmin;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsUser;
import edu.tinkoff.tinkoffbackendacademypetproject.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Работа с комментариями")
@RequestMapping("/v1/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    private final PageMapper pageMapper;

    /**
     * Creates a new comment for a given publication.
     *
     * @param request The request body containing the details of the comment to be created.
     * @return The response containing the created comment.
     * @throws EntityModelNotFoundException If the publication or user referenced in the comment request does not exist.
     */
    @PostMapping
    @Operation(description = "Добавить новый комментарий к публикации", summary = "Добавить новый комментарий к публикации")
    @IsUser
    public CommentResponseDto createComment(@RequestBody @Valid CreateCommentRequestDto request,
                                            @AuthenticationPrincipal Account account) throws EntityModelNotFoundException {
        return commentMapper.toCommentResponseDto(
                commentService.createComment(commentMapper.fromCreateCommentRequestDto(request), account)
        );
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The response containing the retrieved comment.
     * @throws EntityModelNotFoundException If the comment with the specified ID does not exist.
     */
    @GetMapping("/{id}")
    @Operation(description = "Найти комментарий по id", summary = "Найти комментарий по id")
    public CommentResponseDto getComment(@PathVariable
                                         @Min(value = 1, message = "Id комментария не может быть меньше 1")
                                         @NotNull(message = "Id комментария не может быть пустым")
                                         @Schema(description = "Id комментария", example = "1")
                                         Long id) throws EntityModelNotFoundException {
        return commentMapper.toCommentResponseDto(commentService.getComment(id));
    }

    /**
     * Updates the content of a comment.
     *
     * @param request The request containing the updated comment data.
     * @return The response containing the updated comment.
     * @throws EntityModelNotFoundException If the comment to update does not exist.
     */
    @PutMapping
    @Operation(description = "Изменить содержимое комментария", summary = "Изменить содержимое комментария")
    @IsUser
    public CommentResponseDto updateComment(@RequestBody @Valid ChangeCommentRequestDto request) throws EntityModelNotFoundException {
        return commentMapper.toCommentResponseDto(
                commentService.updateComment(commentMapper.fromChangeCommentRequestDto(request))
        );
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     * @throws EntityModelNotFoundException If the comment to delete does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить комментарий по id", summary = "Удалить комментарий по id")
    @IsAdmin
    public void deleteComment(@PathVariable
                              @Min(value = 1, message = "Id комментария не может быть меньше 1")
                              @NotNull(message = "Id комментария не может быть пустым")
                              @Schema(description = "Id комментария", example = "1")
                              Long id) throws EntityModelNotFoundException {
        commentService.deleteComment(id);
    }

    /**
     * Retrieves all comments on a publication.
     *
     * @param request The request object containing the page number, page size, and publication ID.
     * @return A PageResponseDto containing the list of comment response DTOs with pagination information.
     */
    @GetMapping
    @Operation(description = "Получить все комментарии к публикации",
            summary = "Получить все комментарии к публикации")
    public PageResponseDto<CommentResponseDto> getCommentsOnThePublication(@ParameterObject @Valid CommentsOnThePublicationRequestDto request) {
        return pageMapper.toPageResponseDto(commentService.getCommentsOnThePublication(request.getPageNumber(), request.getPageSize(), request.getPublicationId()),
                commentMapper::toCommentResponseDto);
    }
}
