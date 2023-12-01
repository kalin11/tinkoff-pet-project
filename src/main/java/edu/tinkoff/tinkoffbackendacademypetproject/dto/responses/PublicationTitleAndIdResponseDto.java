package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PublicationTitleAndIdResponseDto(Long id,
                                               String title,
                                               @JsonProperty("nickname") String nickname) {
}
