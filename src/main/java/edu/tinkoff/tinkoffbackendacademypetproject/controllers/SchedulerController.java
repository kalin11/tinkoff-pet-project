package edu.tinkoff.tinkoffbackendacademypetproject.controllers;

import edu.tinkoff.tinkoffbackendacademypetproject.dto.requests.SetVerificationTimeRequestDto;
import edu.tinkoff.tinkoffbackendacademypetproject.dto.responses.SetVerificationTimeResponseDto;
import edu.tinkoff.tinkoffbackendacademypetproject.security.annotations.IsAdmin;
import edu.tinkoff.tinkoffbackendacademypetproject.services.SchedulerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Tag(name = "Планировщик", description = "Работа с планировщиком")
@RequestMapping("/v1/scheduler")
@IsAdmin
public class SchedulerController {
    private final SchedulerService schedulerService;

    @PostMapping
    @Operation(description = "Установить время проверки планировщиком", summary = "Установить время проверки планировщиком")
    @IsAdmin
    public SetVerificationTimeResponseDto setVerificationTime(@RequestBody @Valid SetVerificationTimeRequestDto request) throws SchedulerException, ParseException {
        return new SetVerificationTimeResponseDto(schedulerService.setVerificationTime(request.minutes()));
    }
}