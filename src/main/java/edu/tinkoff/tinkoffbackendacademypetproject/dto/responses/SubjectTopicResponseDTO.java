package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * ДТО-ответ про топики предметов
 */

@Getter
@Setter
public class SubjectTopicResponseDTO {
    /**
     * ДТО-ответ предмета
     */
    @Schema(description = "ДТО предмета")
    @JsonProperty("subject")
    private SubjectResponseDTO subjectResponseDTO;

    /**
     * Номер курса
     */
    @Schema(description = "Курс", example = "1")
    @JsonProperty("course_number")
    private Long courseNumber;

    /**
     * ДТО-ответ для типа топика
     */
    @Schema(description = "ДТО типа топика")
    @JsonProperty("topic")
    private TopicTypeResponseDTO topicTypeResponseDTO;
}
