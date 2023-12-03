package edu.tinkoff.tinkoffbackendacademypetproject.config;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final AuthService authService;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.nickname}")
    private String nickname;

    @Override
    public void run(ApplicationArguments args) {
        authService.registerAdmin(
                new Account(null,
                        adminEmail,
                        password,
                        nickname,
                        null,
                        null,
                        null,
                        null,
                        LocalDate.now(),
                        false,
                        Role.ROLE_ADMIN,
                        null,
                        null,
                        null));
    }

}