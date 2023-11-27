package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.AccountRegistrationRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "token", source = "token")
    AccountResponseDto toAccountRegistrationResponseDto(String token);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Account fromAccountRegistrationRequestDto(AccountRegistrationRequestDto account);
}