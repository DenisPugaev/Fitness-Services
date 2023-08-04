package com.example.services.validators;


import com.example.services.dto.SubscriptionDto;

import com.example.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 Полностью переделать
 */
@Component
public class ServiceValidator {

    // доделать валидатор, добавить проверки
    public void validate(SubscriptionDto subscriptionDto) {
        List<String> errors = new ArrayList<>();
        if (subscriptionDto.getPrice().compareTo(BigDecimal.ONE) <= 0) {
            errors.add("Неверно установлена цена подписки! Цена не может быть меньше 0!");
        }
        if (subscriptionDto.getWorkoutCount() < 0) {
            errors.add("Количество тренировок не может быть отрицательным!");
        }
        if (subscriptionDto.getEndDate() == null || subscriptionDto.getEndDate().isBefore(LocalDate.now())) {
            errors.add("Указана некорректная дата");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}

