package edu.tinkoff.tinkoffbackendacademypetproject.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");
    private final String description;
}
