package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountRegistrationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "publications", ignore = true)
    @Mapping(source = "account.nickname", target = "nickname", qualifiedByName = "validateNickname")
    Account fromAccountRegistrationRequestDto(AccountRegistrationRequestDto account);

    @Mapping(source = "role", target = "role", qualifiedByName = "getRole")
    @Mapping(source = "account.firstName", target = "firstName")
    @Mapping(source = "account.lastName", target = "lastName")
    @Mapping(source = "account.middleName", target = "middleName")
    @Mapping(source = "account.birthDate", target = "birthDate", qualifiedByName = "getDate")
    @Mapping(source = "banned", target = "isBanned")
    AccountResponseDto fromAccount(Account account);

    @Named("validateNickname")
    static String validateNickname(String nickname) {
        return nickname.trim();
    }

    @Named("getRole")
    static String getRole(Role role) {
        return role.getDescription();
    }

    @Named("getDate")
    static String getDate(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toString();
    }
}
