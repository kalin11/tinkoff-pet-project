package edu.tinkoff.tinkoffbackendacademypetproject.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectTopicByCourseAndSubjectIdRequestDTO extends PageRequestDto {
    @Min(1)
    @Schema(description = "Курс", example = "1")
    private Long course;

    @Min(1)
    @Schema(description = "Id предмета", example = "1")
    private Long subjectId;

}
