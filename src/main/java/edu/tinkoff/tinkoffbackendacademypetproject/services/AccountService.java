package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Page<Account> getAllUsers(Integer pageNumber, Integer pageSize) {
        return accountRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Transactional
    public Account banUserById(Long id) throws EntityModelNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Пользователя", "id", id));
        account.setBanned(true);
        return accountRepository.save(account);
    }

    @Transactional
    public Account unbanUserById(Long id) throws EntityModelNotFoundException {
        Account account = accountRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Пользователя", "id", id));
        account.setBanned(false);
        return accountRepository.save(account);
    }

}
