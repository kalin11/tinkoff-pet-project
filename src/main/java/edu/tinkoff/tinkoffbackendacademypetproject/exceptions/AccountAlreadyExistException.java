package edu.tinkoff.tinkoffbackendacademypetproject.exceptions;

public class AccountAlreadyExistException extends AlreadyExistsException {
    public AccountAlreadyExistException(String email) {
        super("Аккаунт с данной почтой: " + email + " уже существует");
    }
}
