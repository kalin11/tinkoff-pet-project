package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ДТО для создания нового предмета
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectRequestDTO {
    /**
     * Название предмета
     */
    @Schema(description = "Название предмета", example = "Математика")
    @NotBlank(message = "Название предмета не может быть пустым")
    private String name;
}
