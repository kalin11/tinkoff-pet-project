package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

//todo
public record AccountResponseDto(String nickname,
                                 String description,
                                 @JsonProperty("first_name") String firstName,
                                 @JsonProperty("last_name") String lastName,
                                 @JsonProperty("middle_name") String middleName,
                                 @JsonProperty("birth_date") String birthDate,
                                 @JsonProperty("is_banned") Boolean isBanned,
                                 String role,
                                 @JsonProperty("photo_name_in_directory") String photoNameInDirectory) {
}
