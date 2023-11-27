package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.tinkoff.tinkoffbackendacademypetproject.validation.constraints.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record AccountRegistrationRequestDto(@NotNull
                                            @NotEmpty
                                            @JsonProperty("full_name")
                                            String fullName,

                                            @NotNull
                                            @NotEmpty
                                            String password,
                                            @JsonProperty("matching_password")
                                            String matchingPassword,

                                            @NotNull
                                            @NotEmpty
                                            @ValidEmail
                                            String email) {
}
