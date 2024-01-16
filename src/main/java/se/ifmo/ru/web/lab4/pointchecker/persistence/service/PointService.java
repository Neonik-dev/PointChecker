package se.ifmo.ru.web.lab4.pointchecker.persistence.service;

import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;

import java.util.List;

public interface PointService {
    List<PointDto> getPointsByUserId(User user);

    List<PointDto> addUserPoint(DirtyPointDto dto, User user);
}
