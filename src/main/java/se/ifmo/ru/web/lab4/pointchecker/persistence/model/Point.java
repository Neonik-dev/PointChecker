package se.ifmo.ru.web.lab4.pointchecker.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import se.ifmo.ru.web.lab4.pointchecker.dto.DirtyPointDto;
import se.ifmo.ru.web.lab4.pointchecker.utils.CheckPoint;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x", nullable = false)
    private double x;

    @Column(name = "y", nullable = false)
    private double y;

    @Column(name = "r", nullable = false)
    private double r;

    @Column(name = "is_hit", nullable = false)
    private boolean isHit;

    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    public Point(DirtyPointDto dto, Long userId) {
        x = dto.x();
        y = dto.y();
        r = dto.r();
        isHit = CheckPoint.checkHit(dto);
        this.userId = userId;
    }
}
