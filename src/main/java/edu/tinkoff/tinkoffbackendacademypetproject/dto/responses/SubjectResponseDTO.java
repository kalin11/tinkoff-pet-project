package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ДТО, которое возвращает предмет
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectResponseDTO {
    /**
     * Идентификатор предмета
     */
    @Schema(description = "Идентификатор предмета", example = "1")
    private Long id;

    /**
     * Название предмета
     */
    @Schema(description = "Название предмета", example = "Математика")
    private String name;
}
