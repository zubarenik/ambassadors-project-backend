package ru.ambassadors.competition.backend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "АПИ подтверждения почты")
@RequestMapping("/ambassadors-competition/email")
public interface EmailControllerAPI {
    @PutMapping("/confirmation-email")
    @Operation(description = "Пользователь вызывает эндпойнт из письма на почте")
    void confirmEmail(@Parameter(description = "Айди пользователя") @RequestParam("user-id") String userId);
}
