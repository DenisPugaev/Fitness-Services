package com.example.services.dto;

import com.example.services.entities.Discipline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DisciplineDto {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class SubscriptionDto {
        private Long id;
        private String name;
        private String description;

    }
}
