package edu.tinkoff.tinkoffbackendacademypetproject.config;

import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {
        accountService.registerAdmin(new Account(null, "admin@admin.ru", "admin", "admin", Role.ROLE_ADMIN, null, null));
    }

}