package se.ifmo.ru.web.lab4.pointchecker.persistence.service;

import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;

import java.util.List;

public interface PointService {
    List<PointDto> getUserPoints();

    List<PointDto> addPoint(DirtyPointDto dto);

    Long getUserIdFromToken();
}
