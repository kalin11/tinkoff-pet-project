package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.TopicTypeResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.TopicMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.TopicType;
import edu.tinkoff.tinkoffbackendacademypetproject.services.TopicTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с видами топиков
 */
@RestController
@RequestMapping("/v1/topics")
@RequiredArgsConstructor
@Validated
public class TopicController {
    /**
     * Сервис для работы с типами топиков
     */
    private final TopicTypeService typeService;
    /**
     * Маппер топиков
     */
    private final TopicMapper topicMapper;

    @GetMapping
    @Operation(summary = "Получение всех видов возможных топиков", description = "Получение всех видов возможных топиков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен список всех типов топиков")
    })
    public List<TopicTypeResponseDTO> findAll() {
        List<TopicType> types = typeService.findAll();
        return topicMapper.toListTopicResponseDTO(types);
    }
}
