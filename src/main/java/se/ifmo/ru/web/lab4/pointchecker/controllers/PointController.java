package se.ifmo.ru.web.lab4.pointchecker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.repository.UserRepository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.PointService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "point/")
public class PointController {
    private final PointService pointService;
    private final UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<PointDto> getAllPoints() {
        return pointService.getPointsByUserId(userRepository.getReferenceById(1L));
    }

    @PostMapping(value = "/add")
    public List<PointDto> addPoint(@RequestBody DirtyPointDto dto) {
        return pointService.addUserPoint(dto, userRepository.getReferenceById(1L));
    }
}
