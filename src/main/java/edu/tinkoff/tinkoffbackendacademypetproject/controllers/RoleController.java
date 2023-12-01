package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.RoleResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.model.Account;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Роли", description = "Работа с ролями")
@RequestMapping("/v1/roles")
public class RoleController {

    @GetMapping
    public RoleResponseDto getRolesByToken(@AuthenticationPrincipal Account account) {
        return new RoleResponseDto(account.getRole().getDescription());
    }

}
