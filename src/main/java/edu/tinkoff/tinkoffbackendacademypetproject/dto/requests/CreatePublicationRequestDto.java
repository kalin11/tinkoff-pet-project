package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreatePublicationRequestDto(
        @Schema(description = "Описание публикации", example = "Фото контрольной работы")
        @Size(max = 300, message = "Слишком длинное описание публикации")
        @NotBlank(message = "Описание публикации не может быть пустым")
        String description,
        @JsonProperty("subject_topic_id")
        @Min(value = 1, message = "Id топика не может быть меньше 1")
        @NotNull(message = "Id топика не может быть пустым")
        @Schema(description = "Id топика, в котором сделана публикация", example = "1")
        Long subjectTopicId,
        List<CreateFileRequestDto> files) {
}
