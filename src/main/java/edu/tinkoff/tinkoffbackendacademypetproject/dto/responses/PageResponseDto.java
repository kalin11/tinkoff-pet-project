package edu.tinkoff.tinkoffbackendacademypetproject.dto.responses;


import java.util.List;


public record PageResponseDto<T>(int totalPages, long totalSize, int pageNumber, int pageSize, List<T> content) {
}
