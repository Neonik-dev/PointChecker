package se.ifmo.ru.web.lab4.pointchecker.persistence.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.Point;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.User;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.PointRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.PointService;
import se.ifmo.ru.web.lab4.pointchecker.utils.PointMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointRepository pointRepository;

    @Override
    public List<PointDto> getPointsByUserId(User user) {
        // todo переписать на mapper
        return pointRepository.findPointsByUser(user)
                .stream()
                .map(PointMapper::mapper)
                .toList();
    }

    @Override
    public List<PointDto> addUserPoint(DirtyPointDto dto, User user) {
        // todo ask как правильно реализовать этот момент
        Point point = new Point(dto, user);
        pointRepository.save(point);
        return getPointsByUserId(user);
    }
}
