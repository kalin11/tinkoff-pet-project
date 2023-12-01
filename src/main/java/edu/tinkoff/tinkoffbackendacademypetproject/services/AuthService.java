package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.AccountAlreadyExistException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.BannedAccountException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.LoginFailException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.AccountRepository;
import edu.tinkoff.tinkoffbackendacademypetproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Pair<Account, String> login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            Account account = accountRepository.findByEmail(email);
            if (account == null) {
                throw new LoginFailException();
            }
            if (account.isBanned()) {
                throw new BannedAccountException();
            }
            return Pair.of(account, jwtService.generateToken(account));
        } catch (AuthenticationException e) {
            throw new LoginFailException();
        }
    }

    @Transactional
    public Pair<Account, String> register(Account account) throws AccountAlreadyExistException {
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new AccountAlreadyExistException("почтой", account.getEmail());
        }
        if (accountRepository.findByNicknameAndBanned(account.getNickname(), true) != null) {
            throw new AccountAlreadyExistException("ником", account.getNickname());
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.ROLE_USER);
        return Pair.of(account, jwtService.generateToken(accountRepository.save(account)));
    }

    @Transactional
    public void registerAdmin(Account account) throws AccountAlreadyExistException {
        if (accountRepository.findByEmail(account.getEmail()) == null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
    }
}
