package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FileResponseDto(Long id,
                              @JsonProperty("file_url")
                              String fileUrl,
                              String extension) {
}
