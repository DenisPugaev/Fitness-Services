package com.example.services.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Класс "Дисциплина" представляет сущность дисциплины.
 * Он содержит информацию о названии и описании дисциплины.
 */
@Data
@Entity
@Table(name = "disciplines")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.PERSIST)
    private List<Subscription> subscriptions;

    public Discipline(Long disciplineId, String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Discipline() {
    }
}