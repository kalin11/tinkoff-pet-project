package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.PageRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.AccountResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.PageResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.exceptions.EntityModelNotFoundException;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.AccountMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.mappers.PageMapper;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsAdmin;
import edu.tinkoff.tinkoffbackendacademypetproject.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Работа с комментариями")
@RequestMapping("/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    private final PageMapper pageMapper;
    private final AccountMapper accountMapper;

    @GetMapping
    @Operation(description = "Получение админом списка всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получение всего списка пользователей"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsAdmin
    public PageResponseDto<AccountResponseDto> getAllUsers(@ParameterObject @Valid PageRequestDto pageRequestDto) {
        return pageMapper.toPageResponseDto(
                accountService.getAllUsers(pageRequestDto.getPageNumber(), pageRequestDto.getPageSize()),
                accountMapper::fromAccount
        );
    }

    @PostMapping("/{id}/ban")
    @Operation(description = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно удален пользователь"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsAdmin
    public AccountResponseDto banUser(@PathVariable
                               @Valid
                               @Schema(description = "Id пользователя", example = "1")
                               @Min(1)
                               @NotNull
                               Long id) throws EntityModelNotFoundException {
        return accountMapper.fromAccount(accountService.banUserById(id));
    }

    @PostMapping("/{id}/unban")
    @Operation(description = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успешно удален пользователь"),
            @ApiResponse(responseCode = "400", description = "Что-то пошло не так"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав")
    })
    @IsAdmin
    public AccountResponseDto unbanUser(@PathVariable
                                      @Valid
                                      @Schema(description = "Id пользователя", example = "1")
                                      @Min(1)
                                      @NotNull
                                      Long id) throws EntityModelNotFoundException {
        return accountMapper.fromAccount(accountService.unbanUserById(id));
    }
}
