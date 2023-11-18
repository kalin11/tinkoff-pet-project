package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.CourseResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Course;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Маппер для объекта курса
 */
@Mapper(componentModel = "spring")
public interface CourseMapper {

    /**
     * Конвертация из объекта в ДТО
     *
     * @param course объект курса
     * @return ДТО курса
     */
    CourseResponseDTO toCourseResponseDTO(Course course);

    /**
     * Конвертация списка курсов в список ДТО курсов
     *
     * @param courses список курсов
     * @return список ДТО курсов
     */
    List<CourseResponseDTO> toListOfCourseResponseDTO(List<Course> courses);

}
