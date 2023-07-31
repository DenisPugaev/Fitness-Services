package com.example.services.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс, представляющий сущность "Подписка".
 * Содержит информацию о подписке, такую как связанная дисциплина,
 * дата окончания, цена и количество тренировок.
 */
@Data
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discipline_id", referencedColumnName = "id")
    private Discipline discipline;

    @Column(name = "workout_count")
    private int workoutCount;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "price")
    private BigDecimal price;



    public Subscription(Discipline discipline, LocalDate endDate, BigDecimal price, int workoutCount) {
        this.discipline = discipline;
        this.endDate = endDate;
        this.price = price;
        this.workoutCount = workoutCount;
    }

    public Subscription() {
    }
}