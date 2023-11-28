package edu.tinkoff.tinkoffbackendacademypetproject.config;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final AccountService accountService;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String password;

    @Value("${admin.nickname}")
    private String nickname;

    @Override
    public void run(ApplicationArguments args) {
        accountService.registerAdmin(new Account(null, adminEmail, password, nickname, Role.ROLE_ADMIN, null, null));
    }

}