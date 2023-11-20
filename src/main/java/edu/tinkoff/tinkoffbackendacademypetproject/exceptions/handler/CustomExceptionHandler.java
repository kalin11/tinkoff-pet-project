package edu.tinkoff.tinkoffbackendacademypetproject.exceptions.handler;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.ApiErrorResponse;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.AlreadyExistsException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.SubjectAlreadyExistsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-обработчик ошибок
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Обработчик, если валидация не была пройдена
     *
     * @param e ошибка
     * @return отчет об ошибке
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiErrorResponse handleCourseNumberException(Exception e) {
        String message = "";
        if (e.getMessage().contains("courseNumber")) {
            message = "Номер курса должен быть больше 0";
        }
        return new ApiErrorResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleEntityModelNotFoundException(EntityModelNotFoundException ex) {
        return new ApiErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик, если валидация что-то уже существует
     *
     * @param e ошибка
     * @return отчет об ошибке
     */
    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiErrorResponse handleSubjectAlreadyExistsException(Exception e) {
        return new ApiErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Обработчик ошибок, связанных с неправильным запросом
     *
     * @param ex накопленные ошибки
     * @return в каких полях была допущена ошибка и сообщение ошибки
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
