package com.example.services.dto;

import com.example.services.entities.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto {
    private Long id;
    private Long disciplineId;
    private int workoutCount;
    private LocalDate endDate;
    private BigDecimal price;
}