package se.ifmo.ru.web.lab4.pointchecker.utils;

import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;

public class CheckPoint {
    public static boolean checkHit(DirtyPointDto dto) {
        double x = dto.x();
        double y = dto.y();
        double r = dto.r();

        // Triangle in top-right quadrant
        if (x >= 0 && y >= 0) {
            return (x <= r) && (-x + r >= y);
        }
        // Circle in bottom-right quadrant
        if (x >= 0 && y <= 0) {
            return (x * x + y * y) <= (r * r);
        }
        // Rectangle in bottom-left quadrant
        if (x <= 0 && y <= 0) {
            return (-y <= r / 2) && (-x <= r);
        }
        // Empty in top-left quadrant
        return false;
    }
}
