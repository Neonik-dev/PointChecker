package se.ifmo.ru.web.lab4.pointchecker.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ifmo.ru.web.lab4.pointchecker.persistence.model.Point;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findPointsByUserId(Long userId);
}
