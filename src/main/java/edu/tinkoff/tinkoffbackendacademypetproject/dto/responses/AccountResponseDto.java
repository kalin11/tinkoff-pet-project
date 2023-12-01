package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccountResponseDto(Long id,
                                 String email,
                                 @JsonProperty("nickname")
                                 String nickname,
                                 String role,
                                 @JsonProperty("first_name")
                                 String firstName,
                                 @JsonProperty("last_name")
                                 String lastName,
                                 @JsonProperty("middle_name")
                                 String middleName,
                                 @JsonProperty("birth_date")
                                 String birthDate,
                                 @JsonProperty("is_banned")
                                 boolean isBanned
) {
}
