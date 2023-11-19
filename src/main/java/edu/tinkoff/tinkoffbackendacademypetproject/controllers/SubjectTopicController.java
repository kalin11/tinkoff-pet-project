package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectTopicRequestDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectTopicResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.SubjectTopicMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.SubjectTopic;
import edu.tinkoff.tinkoffbackendacademypetproject.services.SubjectTopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @Operation(summary = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета", description = "Получение всех топиков по предметам с указанным курсом и указанным названием предмета")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение данных", content = @Content)
    })
    public List<SubjectTopicResponseDTO> findAllByCourseNumberAndSubjectName(@Valid @RequestBody SubjectTopicRequestDTO dto) {
        List<SubjectTopic> subjectTopics = topicService.findAllByCourseNumberAndSubjectName(dto.getCourseNumber(), dto.getSubjectName());
        return subjectTopicMapper.getListSubjectTopicResponseDTO(subjectTopics);
    }
}
