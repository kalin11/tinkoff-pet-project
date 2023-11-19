package edu.tinkoff.tinkoffbackendacademypetproject.exceptions;

public class EntityModelNotFoundException extends Exception {

    public EntityModelNotFoundException(String entity, String field, Long valueField) {
        super(entity + " с " + field + ": " + valueField + " не найдено");
    }
}