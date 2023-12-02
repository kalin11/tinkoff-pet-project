package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Файлы", description = "Работа с файлами")
@RequestMapping("/v1/files")
public class FileController {
    private final FileService storageService;

    @GetMapping("/{filename}")
    @Operation(description = "Достать файл из папки", summary = "Достать файл из папки")
    public ResponseEntity<Resource> serveFile(@PathVariable
                                              @Valid
                                              @Schema(description = "Название файла в папке", example = "obi.jpg")
                                              @Size(max = 400, message = "Слишком длинное название для файла")
                                              @NotBlank(message = "Название файла не может быть пустым")
                                              String filename) {
        Resource file = storageService.loadAsResource(filename);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + storageService.getInitialFileName(filename) + "\"").body(file);
    }

}
