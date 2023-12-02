package edu.tinkoff.tinkoffbackendacademypetproject.services;

import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.NotEnoughRightsException;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import edu.tinkoff.tinkoffbackendacademypetproject.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PhotoService photoService;

    public Page<Account> getAllUsers(Integer pageNumber, Integer pageSize) {
        return accountRepository.findByRole(Role.ROLE_USER, PageRequest.of(pageNumber, pageSize));
    }

    @Transactional
    public Account banUserById(Long id) throws EntityModelNotFoundException {
        var account = accountRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Пользователя", "id", Long.toString(id)));
        account.setIsBanned(true);
        return accountRepository.save(account);
    }

    @Transactional
    public Account unbanUserById(Long id) throws EntityModelNotFoundException {
        var account = accountRepository.findById(id).orElseThrow(() -> new EntityModelNotFoundException("Пользователя", "id", Long.toString(id)));
        account.setIsBanned(false);
        return accountRepository.save(account);
    }

    @Transactional
    public Account saveInformationAboutAccount(Account information, MultipartFile photo) throws EntityModelNotFoundException {
        var accountAuth = accountRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (accountAuth.getNickname().equals(information.getNickname())) {
            accountAuth.setDescription(information.getDescription());
            accountAuth.setFirstName(information.getFirstName());
            accountAuth.setLastName(information.getLastName());
            accountAuth.setMiddleName(information.getMiddleName());
            accountAuth.setBirthDate(information.getBirthDate());
            if (photo != null) {
                var photoInProfile = photoService.store(photo);
                if (accountAuth.getProfilePicture() != null) {
                    accountAuth.getProfilePicture().setPhotoNameInDirectory(photoInProfile.getPhotoNameInDirectory());
                    accountAuth.getProfilePicture().setInitialPhotoName(photoInProfile.getInitialPhotoName());
                } else {
                    photoInProfile.setAccount(accountAuth);
                    accountAuth.setProfilePicture(photoInProfile);
                }
            }
        } else {
            throw new NotEnoughRightsException();
        }
        return accountRepository.save(accountAuth);
    }

    @Transactional
    public Account getAccount(String nickname) throws EntityModelNotFoundException {
        try {
            return accountRepository.findByNickname(nickname);
        } catch (Exception e) {
            throw new EntityModelNotFoundException("Пользователя", "nickname", nickname);
        }
    }
}
