package com.example.services.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Класс, представляющий сущность "Подписка".
 * Содержит информацию о подписке, такую как связанная дисциплина,
 * дата окончания, цена и количество тренировок.
 */
@Data
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    ///@ManyToOne(cascade = {CascadeType.ALL}) вполне достаточно persist
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @Column(name = "workout_count")
    private int workoutCount;

    @Column(name = "days_to_expire")
    private Integer DaysToExpire;

    @Column(name = "price")
    private BigDecimal price;





//    public void setDiscipline(Optional<Discipline> discipline) {
//        this.discipline = discipline
//    }

//    public void setDiscipline(Optional <Discipline> discipline) {
//        this.discipline = discipline;
//    }
}
