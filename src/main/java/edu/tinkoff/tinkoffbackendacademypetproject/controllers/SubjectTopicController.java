package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicBySubjectIdRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PageResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectTopicResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PageMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.SubjectTopicMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopicEntity;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsAdmin;
import edu.tinkoff.tinkoffbackendacademypetproject.services.SubjectTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с топиками предметов
 */
@RestController
@RequestMapping("/v1/subject-topics")
@RequiredArgsConstructor
@Validated
public class SubjectTopicController {
    /**
     * Сервис для работы с топиками предметов
     */
    private final SubjectTopicService topicService;

    /**
     * Маппер топиков предметов
     */
    private final SubjectTopicMapper subjectTopicMapper;
    private final PageMapper pageMapper;

    @GetMapping
    @Operation(summary = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета", description = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных", content = @Content)
    })
    public PageResponseDto<SubjectTopicResponseDto> findAllBySubjectId(@ParameterObject @Valid SubjectTopicBySubjectIdRequestDto dto) {
        return pageMapper.toPageResponseDto(topicService.findAllBySubjectId(
                        dto.getPageNumber(),
                        dto.getPageSize(),
                        dto.getSubjectId()),
                subjectTopicMapper::getSubjectTopicResponseDTO
        );
    }

    @IsAdmin
    @PostMapping
    public SubjectTopicResponseDto createSubjectTopic(@Valid @RequestBody SubjectTopicRequestDto dto) throws EntityModelNotFoundException {
        SubjectTopicEntity savedTopic = topicService.createSubjectTopic(subjectTopicMapper.getSubjectTopicFromDTO(dto));
        return subjectTopicMapper.getSubjectTopicResponseDTO(savedTopic);
    }

}
