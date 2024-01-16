package se.ifmo.ru.web.lab4.pointchecker.utils;

import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.Point;

public class PointMapper {
    public static PointDto mapper(Point point) {
        return new PointDto(point.getX(), point.getY(), point.getR(), point.isHit());
    }
}
