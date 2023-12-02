package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetAllUserResponseDto(Long id,
                                    String email,
                                    String nickname,
                                    @JsonProperty("is_banned")
                                    Boolean isBanned) {
}
