package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.services.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Tag(name = "Фото", description = "Работа с фото")
@RequestMapping("/v1/photo")
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping(
            value = "/{filename}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @Operation(description = "Достать файл из папки", summary = "Достать файл из папки")
    public @ResponseBody byte[] getImage(@PathVariable
                                         @Valid
                                         @Schema(description = "Название файла в папке", example = "obi.jpg")
                                         @Size(max = 400, message = "Слишком длинное название для файла")
                                         @NotBlank(message = "Название файла не может быть пустым")
                                         String filename) throws IOException {
        return IOUtils.toByteArray(photoService.loadAsResource(filename).getInputStream());
    }

}
