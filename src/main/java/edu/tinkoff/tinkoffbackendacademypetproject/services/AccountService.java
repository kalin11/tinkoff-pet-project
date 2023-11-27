package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.AccountAlreadyExistException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.AccountRepository;
import edu.tinkoff.tinkoffbackendacademypetproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String register(Account account) throws AccountAlreadyExistException {
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new AccountAlreadyExistException(account.getEmail());
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(Role.ROLE_USER);
        return jwtService.generateToken(accountRepository.save(account));
    }

    public void registerAdmin(Account account) throws AccountAlreadyExistException {
        if (accountRepository.findByEmail(account.getEmail()) == null) {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            accountRepository.save(account);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email);
    }
}