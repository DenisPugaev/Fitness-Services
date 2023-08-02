package com.example.services.validators;


import com.example.services.dto.SubscriptionDto;
import com.example.services.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 Полностью переделать
 */
@Component
public class ServiceValidator {

    public void validate(SubscriptionDto SubscriptionDto) {
        List<String> errors = new ArrayList<>();
        if (SubscriptionDto.getPrice().compareTo(BigDecimal.ONE) <= 0) {
            errors.add("Неверно установлена цена услуги! Цена не может быть меньше 1!");
        }
        if (SubscriptionDto.getWorkoutCount() < 0) {
            errors.add("Количество тренировок не может быть отрицательным!");
        }
////        if (SubscriptionDto.getDescription().isBlank()) {
////            errors.add("Описание услуги не может быть пустым!");
//        }
//        if(!errors.isEmpty()){
//            throw new ValidationException(errors);
//        }
    }
}

