package se.ifmo.ru.web.lab4.pointchecker.persistence.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.Point;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.PointRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.PointService;
import se.ifmo.ru.web.lab4.pointchecker.utils.PointMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    @Override
    public List<PointDto> getUserPoints() {
        // todo переписать на mapper
        return pointRepository.findPointsByUserId(getUserIdFromToken())
                .stream()
                .map(PointMapper::mapper)
                .toList();
    }

    @Override
    public List<PointDto> addPoint(DirtyPointDto dto) {
        // todo ask как правильно реализовать этот момент

        Point point = new Point(dto, getUserIdFromToken());
        pointRepository.save(point);
        return getUserPoints();
    }

    @Override
    public Long getUserIdFromToken() {
        Claims credentials = (Claims) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return Long.parseLong((String) credentials.get("userId"));
    }
}
