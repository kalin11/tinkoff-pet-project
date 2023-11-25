package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * ДТО для создания нового предмета
 */
public record SubjectRequestDTO(@Schema(description = "Название предмета", example = "Математика")
                                @NotBlank(message = "Название предмета не может быть пустым")
                                String name,
                                @JsonProperty("course_number")
                                @Min(value = 1, message = "Номер курса не может быть меньше 1")
                                @NotNull(message = "Номер курса не может быть пустым")
                                @Schema(description = "Номер курса, который содержит этот предмет", example = "1")
                                Integer courseNumber) {
}
