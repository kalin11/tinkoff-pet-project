package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.CreateFileRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.FileResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileResponseDto toFileResponseDto(File file);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publication", ignore = true)
    File fromCreateFileRequestDto(CreateFileRequestDto fileRequestDto);
}
