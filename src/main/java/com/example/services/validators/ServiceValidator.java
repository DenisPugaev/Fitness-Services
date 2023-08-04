package com.example.services.validators;


import com.example.services.dto.SubscriptionResponse;
import com.example.services.entities.Subscription;
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


    public void validate(Subscription subscription) {
        List<String> errors = new ArrayList<>();
        if (subscription.getPrice().compareTo(BigDecimal.ONE) <= 0) {
            errors.add("Неверно установлена цена абонемента! Цена не может быть меньше 0!");
        }
        if (subscription.getWorkoutCount() < 0) {
            errors.add("Количество тренировок не может быть отрицательным!");
        }
        if (subscription.getDaysToExpire() == null || subscription.getDaysToExpire()<0) {
            errors.add("Количество дней до окончания абонемента не может быть отрицательным!");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}

