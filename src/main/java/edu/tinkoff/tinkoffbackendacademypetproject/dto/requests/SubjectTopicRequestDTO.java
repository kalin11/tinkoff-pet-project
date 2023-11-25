package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

/**
 * Класс для получения всех секций с указанным предметом и курсом
 */
public record SubjectTopicRequestDTO(/**
                                      * Название предмета
                                      */
                                     @Min(1)
                                     @Schema(description = "Id предмета", example = "1")
                                     @JsonProperty("subject_id")
                                     Long subjectId,
                                     @Min(1)
                                     @Schema(description = "Id типа топика", example = "2")
                                     @JsonProperty("topic_id")
                                     Long topicId) {

}
