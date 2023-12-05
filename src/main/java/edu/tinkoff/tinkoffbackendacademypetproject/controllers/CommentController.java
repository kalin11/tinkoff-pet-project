package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.ChangeCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CommentsOnThePublicationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreateCommentRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.CommentResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PageResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.BannedAccountException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.CommentMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PageMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsAdmin;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsUser;
import edu.tinkoff.tinkoffbackendacademypetproject.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping
    @Operation(description = "Добавить новый комментарий к публикации", summary = "Добавить новый комментарий к публикации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно добавлен новый комментарий к публикации"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsUser
    public CommentResponseDto createComment(@RequestBody @Valid CreateCommentRequestDto request,
                                            @AuthenticationPrincipal Account account) throws EntityModelNotFoundException {
        if (account.getIsBanned()) {
            throw new BannedAccountException();
        }
        return commentMapper.toCommentResponseDto(
                commentService.createComment(commentMapper.fromCreateCommentRequestDto(request), account)
        );
    }

    @GetMapping("/{id}")
    @Operation(description = "Найти комментарий по id", summary = "Найти комментарий по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно найден комментарий по id"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
    })
    public CommentResponseDto getComment(@PathVariable
                                         @Min(value = 1, message = "Id комментария не может быть меньше 1")
                                         @NotNull(message = "Id комментария не может быть пустым")
                                         @Schema(description = "Id комментария", example = "1")
                                         Long id) throws EntityModelNotFoundException {
        return commentMapper.toCommentResponseDto(commentService.getComment(id));
    }

    @PutMapping
    @Operation(description = "Изменить содержимое комментария", summary = "Изменить содержимое комментария")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно изменен комментарий"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsAdmin
    public CommentResponseDto updateComment(@RequestBody @Valid ChangeCommentRequestDto request) throws EntityModelNotFoundException {
        return commentMapper.toCommentResponseDto(
                commentService.updateComment(commentMapper.fromChangeCommentRequestDto(request))
        );
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить комментарий по id", summary = "Удалить комментарий по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Успешно удален комментарий по id"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsAdmin
    public void deleteComment(@PathVariable
                              @Min(value = 1, message = "Id комментария не может быть меньше 1")
                              @NotNull(message = "Id комментария не может быть пустым")
                              @Schema(description = "Id комментария", example = "1")
                              Long id) throws EntityModelNotFoundException {
        commentService.deleteComment(id);
    }

    @GetMapping
    @Operation(description = "Получить все комментарии к публикации",
            summary = "Получить все комментарии к публикации")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получены все комментарии к публикации"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так")
    })
    public PageResponseDto<CommentResponseDto> getCommentsOnThePublication(@ParameterObject @Valid CommentsOnThePublicationRequestDto request) {
        return pageMapper.toPageResponseDto(commentService.getCommentsOnThePublication(request.getPageNumber(), request.getPageSize(), request.getPublicationId()),
                commentMapper::toCommentResponseDto);
    }
}
