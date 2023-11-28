package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import edu.tinkoff.tinkoffbackendacademypetproject.validation.constraints.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountLoginRequestDto(@Size(max = 150, message = "Слишком длинный email")
                                     @NotBlank(message = "Email не должен быть пустым")
                                     @ValidEmail
                                     String email,
                                     @Size(max = 50, message = "Слишком длинный пароль")
                                     @NotBlank(message = "Пароль не должен быть пустым")
                                     String password) {
}
