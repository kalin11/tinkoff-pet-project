package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountLoginRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountRegistrationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AuthResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.AccountMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Авторизация", description = "Работа с авторизацией")
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final AccountMapper accountMapper;

    @PostMapping("/register")
    @Operation(description = "Зарегистрировать нового пользователя", summary = "Зарегистрировать нового пользователя")
    public AuthResponseDto registerUserAccount(@RequestBody @Valid AccountRegistrationRequestDto request, HttpServletResponse response) {
        var answer = authService.register(request.email().trim(), request.nickname().trim(), request.password().trim());
        addCookie(answer.getSecond(), response);
        return new AuthResponseDto(answer.getFirst().getEmail(), answer.getFirst().getRole().getDescription());
    }

    @PostMapping("/login")
    @Operation(description = "Войти на сайт", summary = "Войти на сайт")
    public AuthResponseDto loginUserAccount(@RequestBody @Valid AccountLoginRequestDto request, HttpServletResponse response) {
        var answer = authService.login(request.email().trim(), request.password().trim());
        addCookie(answer.getSecond(), response);
        return new AuthResponseDto(answer.getFirst().getEmail(), answer.getFirst().getRole().getDescription());
    }

    private void addCookie(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

}
