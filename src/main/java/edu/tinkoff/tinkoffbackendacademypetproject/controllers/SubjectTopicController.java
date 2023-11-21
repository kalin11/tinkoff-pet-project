package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicRequestDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectTopicResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.SubjectTopicMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.services.SubjectTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @Operation(summary = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета", description = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных", content = @Content)
    })
    public List<SubjectTopicResponseDTO> findAllByCourseNumberAndSubjectId(@Valid @RequestParam(required = false, value = "course") @Min(1) Long course,
                                                                           @Valid @RequestParam(required = false, value = "subjectId") @Min(1) Long subjectId
    ) {
        List<SubjectTopic> subjectTopics = topicService.findAllByCourseNumberAndSubjectId(course, subjectId);
        return subjectTopicMapper.getListSubjectTopicResponseDTO(subjectTopics);
    }

    @PostMapping
    public SubjectTopicResponseDTO createSubjectTopic(@Valid @RequestBody SubjectTopicRequestDTO dto) throws EntityModelNotFoundException {
        SubjectTopic savedTopic = topicService.createSubjectTopic(subjectTopicMapper.getSubjectTopicFromDTO(dto));
        return subjectTopicMapper.getSubjectTopicResponseDTO(savedTopic);
    }

}
