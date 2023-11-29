package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountRegistrationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "publications", ignore = true)
    @Mapping(source = "account.fullName", target = "fullName", qualifiedByName = "validateFullName")
    Account fromAccountRegistrationRequestDto(AccountRegistrationRequestDto account);

    @Named("validateFullName")
    static String validateFullName(String fullName) {
        return fullName.trim();
    }
}
