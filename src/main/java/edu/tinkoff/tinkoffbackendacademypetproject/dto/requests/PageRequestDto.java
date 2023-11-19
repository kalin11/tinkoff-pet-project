package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PageRequestDto {
    /**
     * текущая страница
     */
    @JsonProperty("page_number")
    @Min(value = 0, message = "'page_number' должно быть больше или равно 0")
    @Schema(description = "Номер страницы", example = "0")
    protected Integer pageNumber = 0;

    /**
     * максимальное кол-во элементов на странице
     */
    @JsonProperty("page_size")
    @Min(value = 1, message = "'page_size' должно быть больше или равно 1")
    @Max(value = 5000, message = "'page_size' должно быть меньше или равно 5000")
    @Schema(description = "Кол-во элементов на странице", example = "50")
    protected Integer pageSize = 50;
}
