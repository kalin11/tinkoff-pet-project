package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс для получения всех секций с указанным предметом и курсом
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectTopicRequestDTO {
    /**
     * Номер курса
     */
    @Min(1)
    @Schema(description = "Курс", example = "1")
    @JsonProperty("course_number")
    private Long courseNumber;

    /**
     * Название предмета
     */
    @Min(1)
    @Schema(description = "Id предмета", example = "1")
    @JsonProperty("subject_id")
    private Long subjectId;
}
