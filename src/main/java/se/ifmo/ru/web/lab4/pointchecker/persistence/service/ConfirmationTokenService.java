package se.ifmo.ru.web.lab4.pointchecker.persistence.service;

import se.ifmo.ru.web.lab4.pointchecker.persistence.model.ConfirmationToken;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

public interface ConfirmationTokenService {
    ConfirmationToken createToken(User user);

    String checkConfirmationToken(String confirmationToken);
}

