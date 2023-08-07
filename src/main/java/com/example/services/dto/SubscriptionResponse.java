package com.example.services.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SubscriptionResponse {

    private Long id;
    private String discipline;
    private int workoutCount;
    private Integer daysToExpire;
    private BigDecimal price;
}
