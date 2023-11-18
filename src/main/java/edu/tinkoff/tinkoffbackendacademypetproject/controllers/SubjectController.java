package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectRequestDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.SubjectMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import edu.tinkoff.tinkoffbackendacademypetproject.services.SubjectService;
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
 * Контроллер для работы с предметами
 */
@RestController
@RequestMapping("/v1/subjects")
@RequiredArgsConstructor
@Validated
public class SubjectController {
    /**
     * Сервис для работы с предметами
     */
    private final SubjectService subjectService;
    /**
     * Маппер для работы с предметами
     */
    private final SubjectMapper subjectMapper;

    /**
     * Получение всех предметов, которые есть на указанном курсе
     *
     * @param courseNumber номер курса
     * @return список предметов
     */
    @GetMapping
    @Operation(summary = "Получение предметов на указанном курсе", description = "Возвращает список предметов, которые ведутся на указанном курсе. Если же не указан курс, то возвращаются все предметы")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос был успешно выполнен"),
            @ApiResponse(responseCode = "400", description = "Запрос имеет не валидные параметры", content = @Content),
    })
    public List<SubjectResponseDTO> findAllByCourseNumber(@Valid @RequestParam(required = false, value = "course") @Min(1) Long courseNumber) {
        List<Subject> subjects = subjectService.findAllByCourseNumber(courseNumber);
        return subjectMapper.toListSubjectResponseDTO(subjects);
    }

    /**
     * Создание нового предмета
     *
     * @param dto дто, содержащее название предмета
     * @return сохраненный предмет
     */
    @PostMapping
    @Operation(summary = "Создание нового предмета", description = "Создание нового предмета на каком-то курсе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Предмет был успешно создан"),
            @ApiResponse(responseCode = "400", description = "Такой предмет уже существует", content = @Content)
    })
    public SubjectResponseDTO createSubject(@Valid @RequestBody SubjectRequestDTO dto) {
        Subject subject = subjectMapper.toSubject(dto);
        var savedSubject = subjectService.createSubject(subject);
        return subjectMapper.toSubjectResponseDTO(savedSubject);
    }
}
