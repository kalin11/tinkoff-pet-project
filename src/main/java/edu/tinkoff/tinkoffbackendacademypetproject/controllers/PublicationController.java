package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreatePublicationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.PublicationsInOneCategoryRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PageResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PublicationTitleAndIdResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PageMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PublicationMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.services.PublicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Публикации", description = "Работа с публикациями")
@RequestMapping("/v1/publications")
public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;
    private final PageMapper pageMapper;

    /**
     * This method is used to create a new publication.
     *
     * @param request The request object containing the details of the publication to be created.
     * @return The response object containing the details of the created publication.
     * @throws EntityModelNotFoundException If the publication could not be found.
     */
    @PostMapping(consumes = {"multipart/form-data"})
    @Operation(description = "Добавить новую публикацию", summary = "Добавить новую публикацию")
    public PublicationResponseDto createPublication(@ModelAttribute CreatePublicationRequestDto request) throws EntityModelNotFoundException {
        return publicationMapper.toPublicationResponseDto(
                publicationService.createPublication(publicationMapper.fromCreatePublicationRequestDto(request), request.files())
        );
    }

    /**
     * This method is used to retrieve a publication by its ID.
     *
     * @param id The ID of the publication to retrieve.
     * @return The response object containing the details of the publication.
     * @throws EntityModelNotFoundException If the publication could not be found.
     */
    @GetMapping("/{id}")
    @Operation(description = "Найти публикацию по id", summary = "Найти публикацию по id")
    public PublicationResponseDto getPublication(@PathVariable
                                                 @Min(value = 1, message = "Id публикации не может быть меньше 1")
                                                 @NotNull(message = "Id публикации не может быть пустой")
                                                 @Schema(description = "Id публикации", example = "1")
                                                 Long id) throws EntityModelNotFoundException {
        return publicationMapper.toPublicationResponseDto(publicationService.getPublication(id));
    }


    /**
     * This method is used to delete a publication by its ID.
     *
     * @param id The ID of the publication to delete.
     * @throws EntityModelNotFoundException If the publication could not be found.
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить публикацию по id", summary = "Удалить публикацию по id")
    public void deletePublication(@PathVariable
                                  @Min(value = 1, message = "Id публикации не может быть меньше 1")
                                  @NotNull(message = "Id публикации не может быть пустой")
                                  @Schema(description = "Id публикации", example = "1")
                                  Long id) throws EntityModelNotFoundException {
        publicationService.deletePublication(id);
    }

    /**
     * This method is used to get all publications in a specific topic by the topic ID.
     *
     * @param publications The request DTO object containing the page number, page size, and topic ID.
     * @return The DTO object containing the paginated list of publications in the specified topic.
     */
    @GetMapping
    @Operation(description = "Найти все публикации в заданном топике по id",
            summary = "Найти все публикации в заданном топике по id")
    public PageResponseDto<PublicationTitleAndIdResponseDto> getPublicationsInOneTopic(@ParameterObject PublicationsInOneCategoryRequestDto publications) {
        return pageMapper.toPageResponseDto(publicationService.getPublicationsInOneCategory(
                        publications.getPageNumber(),
                        publications.getPageSize(),
                        publications.getSubjectTopicId()),
                publicationMapper::toPublicationTitleAndIdResponseDto);
    }

}
