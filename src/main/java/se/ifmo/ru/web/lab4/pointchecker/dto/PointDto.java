package se.ifmo.ru.web.lab4.pointchecker.dto;

public record PointDto(
        double x,
        Integer y,
        double r,
        boolean isHit
) {
}
