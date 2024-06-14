package se.ifmo.ru.web.lab4.pointchecker.utils;

import org.junit.jupiter.api.Test;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckPointTest {
    @Test
    void topRightQuadrantFirst() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(1d, 1d, 1d);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertFalse(result);
    }

    @Test
    void topRightQuadrantSecond() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(0.001, 0.001, 2.5);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertTrue(result);
    }

    @Test
    void topLeftQuadrantFirst() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(-0.001, 0.001, 2.5);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertFalse(result);
    }

    @Test
    void topLeftQuadrantSecond() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(-2.5, 2.99999999, 2.5);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertFalse(result);
    }

    @Test
    void bottomRightQuadrantFirst() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(2.5, -2.99999999, 2.5);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertFalse(result);
    }

    @Test
    void bottomRightQuadrantSecond() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(0.005, -0.00009, 2.5);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertTrue(result);
    }

    @Test
    void bottomLeftQuadrantFirst() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(-0.1, -0.1, 2d);
        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertTrue(result);
    }

    @Test
    void bottomLeftQuadrantSecond() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(-2.9, -2.9, 1d);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertFalse(result);
    }

    @Test
    void centerFirst() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(0d, 0d, 1d);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertTrue(result);
    }

    @Test
    void centerSecond() {
        DirtyPointDto dirtyPointDto = new DirtyPointDto(0d, 0d, 0d);

        boolean result = CheckPoint.checkHit(dirtyPointDto);
        assertTrue(result);
    }
}
