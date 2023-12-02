package edu.tinkoff.tinkoffbackendacademypetproject.mappers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SaveInformationAboutAccountRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.GetAllUserResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.StatusAccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;

//todo
@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Named("getRole")
    static String getRole(Role role) {
        return role.getDescription();
    }

    @Named("getDate")
    static String getDate(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.toString();
    }

    @Mapping(target = "photoNameInDirectory", source = "profilePicture.photoNameInDirectory")
    @Mapping(source = "role", target = "role", qualifiedByName = "getRole")
    @Mapping(source = "birthDate", target = "birthDate", qualifiedByName = "getDate")
    AccountResponseDto toAccountResponseDto(Account account);

    GetAllUserResponseDto toGetAllUserResponseDto(Account account);

    StatusAccountResponseDto toStatusAccountResponseDto(Account account);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "publications", ignore = true)
    @Mapping(target = "profilePicture", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isBanned", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Account fromSaveInformationAboutAccountRequestDto(SaveInformationAboutAccountRequestDto saveInformationAboutAccountRequestDto);
}
