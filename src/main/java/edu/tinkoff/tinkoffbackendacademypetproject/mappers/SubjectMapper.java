package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SubjectRequestDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SubjectResponseDTO;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Маппер для предмета
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper {

    /**
     * Конвертация объекта Subject в ДТО-ответ
     *
     * @param subject объект предмета
     * @return ДТО
     */
    SubjectResponseDTO toSubjectResponseDTO(Subject subject);

    /**
     * Конвертация списка объектов Subject в список DTO объектов
     *
     * @param subjects список объектов Subject
     * @return список DTO объектов
     */
    List<SubjectResponseDTO> toListSubjectResponseDTO(List<Subject> subjects);

    /**
     * Конвертация из ДТО в entity-класс
     *
     * @param dto ДТО
     * @return объекта класса Subject
     */
    @Mapping(target = "name", source = "dto.name", qualifiedByName = "getSubjectName")
    Subject toSubject(SubjectRequestDTO dto);

    /**
     * Перевод названия предмета в верхний регистр
     *
     * @param name название предмета
     * @return название предмета в верхнем регистре
     */
    @Named("getSubjectName")
    static String getSubjectName(String name) {
        return name.trim().toUpperCase();
    }
}
