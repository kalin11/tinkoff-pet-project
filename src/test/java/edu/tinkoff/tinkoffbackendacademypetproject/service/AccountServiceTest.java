package edu.tinkoff.tinkoffbackendacademypetproject.service;

import edu.tinkoff.tinkoffbackendacademypetproject.config.PostgresTestConfig;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.AccountAlreadyExistException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.AccountRepository;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(initializers = PostgresTestConfig.Initializer.class)
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    @AfterEach
    public void clear() {
        accountRepository.deleteAll();
        jdbcTemplate.execute("ALTER SEQUENCE account_pk_seq RESTART");
    }

    @DisplayName("Register account")
    @ParameterizedTest(name = "{index} - account register {2}")
    @CsvSource(value = {
            "dan@dan.ru, 100248, Daniil Korshunov",
            "abc1@tink.com, 123, Oleg Tink"
    })
    public void registerTest(String email, String password, String fullName) {
        // given
        var accountGiven = new Account(null, email, password, fullName, null, null, null);

        // when
        accountService.register(accountGiven);

        // then
        var accounts = accountRepository.findAll();
        assertEquals(1, accounts.size());
        var account = accounts.get(0);
        assertEquals(1, account.getId());
        assertEquals(email, account.getEmail());
        assertEquals(fullName, account.getFullName());
    }

    @DisplayName("Register account with throw")
    @ParameterizedTest(name = "{index} - account not register {2}")
    @CsvSource(value = {
            "dan@dan.ru, 100248, Daniil Korshunov",
            "abc1@tink.com, 123, Oleg Tink"
    })
    public void registerWithThrowTest(String email, String password, String fullName) {
        // given
        var accountGiven = new Account(null, email, password, fullName, Role.ROLE_USER, null, null);
        accountRepository.save(accountGiven);
        accountGiven.setRole(null);

        // when
        var exception = assertThrows(
                AccountAlreadyExistException.class,
                () -> accountService.register(accountGiven)
        );

        // then
        assertEquals("Аккаунт с данной почтой: " + email + " уже существует", exception.getMessage());
    }

    @DisplayName("Load user by user name")
    @ParameterizedTest(name = "{index} - load user by user name {0}")
    @ValueSource(strings = {"dan@dan.ru", "daad@dddd.com"})
    public void loadUserByUsernameTest(String email) {
        // given
        var accountGiven = new Account(null, email, "123", "Daniil Korsh", Role.ROLE_USER, null, null);
        accountRepository.save(accountGiven);

        // when
        var accountWhen = accountService.loadUserByUsername(email);

        // then
        var account = accountRepository.findById(1L);
        assertTrue(account.isPresent());
        var accountGet = account.get();
        assertEquals(accountWhen.getUsername(), accountGet.getUsername());
    }

    @DisplayName("Register admin")
    @Test
    public void registerAdminTest() {
        // given

        // when
        accountService.registerAdmin(new Account(null, "admin@admin.ru", "admin", "admin", Role.ROLE_ADMIN, null, null));

        // then
        var accounts = accountRepository.findAll();
        assertEquals(1, accounts.size());
        var account = accounts.get(0);
        assertEquals(1, account.getId());
        assertEquals("admin@admin.ru", account.getEmail());
        assertEquals("admin", account.getFullName());
    }
}

