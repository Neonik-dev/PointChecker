package se.ifmo.ru.web.lab4.pointchecker.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.dto.PointDto;
import se.ifmo.ru.web.lab4.pointchecker.persistence.service.PointService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/point")
public class PointController {
    private final PointService pointService;

    @GetMapping(value = "/all")
    public List<PointDto> getAllPoints() {
        return pointService.getUserPoints();
    }

    @PostMapping(value = "/add")
    public List<PointDto> addPoint(@RequestBody @Valid DirtyPointDto dto) {
        return pointService.addPoint(dto);
    }
}
