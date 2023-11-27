package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AccountLoginRequestDto(@NotNull
                                     @NotEmpty
                                     String email,
                                     @NotNull
                                     @NotEmpty
                                     String password) {
}
