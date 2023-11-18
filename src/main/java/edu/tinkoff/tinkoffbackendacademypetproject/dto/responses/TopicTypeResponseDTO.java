package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ДТО для получения типа топика
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicTypeResponseDTO {
    /**
     * Идентификатор типа топика
     */
    @Schema(name = "Идентификатор типа топика", example = "1")
    private Long id;

    /**
     * Тип топика
     */
    @Schema(name = "Тип топика", example = "Контрольная работа")
    @JsonProperty(value = "topic_type")
    private String topicType;
}
