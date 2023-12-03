package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SetVerificationTimeRequestDto(@Min(value = 1, message = "Количество минут не может быть меньше 1")
                                            @NotNull(message = "Количество минут не может быть пустым")
                                            @Schema(description = "Через сколько минут проверять, что комментарию больше двух лет", example = "2")
                                            @Max(value = 59, message = "Количество минут не может быть больше 59")
                                            Integer minutes) {
}