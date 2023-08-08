package com.example.services.converters;

import com.example.services.dto.SubscriptionDto;
import com.example.services.entities.Discipline;
import com.example.services.entities.Subscription;
import com.example.services.repository.DisciplineRepository;
import org.springframework.stereotype.Component;

/**
 * ПЕРЕДЕЛАТЬ
 *
 * Класс "SubscriptionConverter" представляет сущность конвертера подписки.
 * Он отвечает за преобразование между объектами класса Subscription и SubscriptionDto.
 */
@Component
public class SubscriptionConverter {
   private DisciplineRepository disciplineRepository;

    /**
     * Метод dtoInEntity выполняет преобразование объекта SubscriptionDto в объект Subscription.
     *
     * @param subscriptionDto объект SubscriptionDto
     * @return объект Subscription
     */
    public  Subscription dtoInEntity(SubscriptionDto subscriptionDto) {
        // Проверить и исправить null
        Discipline discipline = new Discipline(null, null, null);
        return new Subscription(discipline, subscriptionDto.getEndDate(), subscriptionDto.getPrice(), subscriptionDto.getWorkoutCount());
    }

    /**
     * Метод entityInDto выполняет преобразование объекта Subscription в объект SubscriptionDto.
     *
     * @param subscription объект Subscription
     * @return объект SubscriptionDto
     */
    public  SubscriptionDto entityInDto(Subscription subscription) {
        Long disciplineId = subscription.getDiscipline() != null ? subscription.getDiscipline().getId() : null;
        return new SubscriptionDto(subscription.getId(), disciplineId, subscription.getWorkoutCount(), subscription.getEndDate(), subscription.getPrice());
    }
}
