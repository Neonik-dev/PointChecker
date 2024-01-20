package se.ifmo.ru.web.lab4.pointchecker.dto;

public record PointDto(
        Double x,
        Double y,
        Double r,
        Boolean isHit
) {
}
