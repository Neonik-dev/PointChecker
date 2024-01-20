package se.ifmo.ru.web.lab4.pointchecker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.ConfirmationTokenService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/confirm-account")
public class ConfirmController {
    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping
    public String checkConfirmationToken(@RequestParam("token") String confirmationToken) {
        return confirmationTokenService.checkConfirmationToken(confirmationToken);
    }
}
