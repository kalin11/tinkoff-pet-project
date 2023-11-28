package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountLoginRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountRegistrationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.AccountMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AccountService;
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
@Tag(name = "Аккаунты", description = "Работа с аккаунтами")
@RequestMapping("/v1/auth")
public class AccountController {
    private final AccountService accountService;
    private final AuthService authService;
    private final AccountMapper accountMapper;

    @PostMapping("/register")
    @Operation(description = "Зарегистрировать нового пользователя", summary = "Зарегистрировать нового пользователя")
    public AccountResponseDto registerUserAccount(@RequestBody @Valid AccountRegistrationRequestDto request, HttpServletResponse response) {
        String token = accountService.register(accountMapper.fromAccountRegistrationRequestDto(request));
        addCookie(token, response);
        return accountMapper.toAccountRegistrationResponseDto(token);
    }

    @PostMapping("/login")
    @Operation(description = "Войти на сайт", summary = "Войти на сайт")
    public AccountResponseDto loginUserAccount(@RequestBody @Valid AccountLoginRequestDto request, HttpServletResponse response) {
        String token = authService.login(request.email(), request.password());
        addCookie(token, response);
        return accountMapper.toAccountRegistrationResponseDto(token);
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
