package se.ifmo.ru.web.lab4.pointchecker.persistence.service;

import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

public interface EmailService {
    void sendSimpleMessage(User user);
}
