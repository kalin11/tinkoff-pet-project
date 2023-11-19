package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateFileRequestDto(@JsonProperty("file_url")
                                   @Schema(description = "Путь к прикрепляемому файлу", example = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
                                   @Size(max = 300, message = "Слишком длинный путь к файлу")
                                   @NotBlank(message = "Путь к файлу не может быть пустым")
                                   String fileUrl,
                                   @Schema(description = "Расширение прикрепляемого файла", example = "png")
                                   @Size(max = 30, message = "Слишком длинное название расширения файла")
                                   @NotBlank(message = "Расширение файла не может быть пустым")
                                   String extension) {
}
