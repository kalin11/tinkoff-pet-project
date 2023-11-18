package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ДТО для получения курса
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseResponseDTO {
    /**
     * Номер курса
     */
    @Schema(name = "Номер курса", example = "1")
    @JsonProperty(value = "course_number")
    private Long courseNumber;

    /**
     * Описание курса
     */
    @Schema(name = "Описание курса", example = "1 курс бакалавриата")
    private String description;
}
