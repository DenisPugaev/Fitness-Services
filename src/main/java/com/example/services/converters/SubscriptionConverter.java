package com.example.services.converters;

import com.example.services.dto.SubscriptionDto;
import com.example.services.entities.Discipline;
import com.example.services.entities.Subscription;
import org.springframework.stereotype.Component;

/**
 * ПЕРЕДЕЛАТЬ
 *
 * Класс "SubscriptionConverter" представляет сущность конвертера подписки.
 * Он отвечает за преобразование между объектами класса Subscription и SubscriptionDto.
 */
@Component
public class SubscriptionConverter {

    /**
     * Метод dtoInEntity выполняет преобразование объекта SubscriptionDto в объект Subscription.
     *
     * @param subscriptionDto объект SubscriptionDto
     * @return объект Subscription
     */
    public static Subscription dtoInEntity(SubscriptionDto subscriptionDto) {
        Discipline discipline = new Discipline(subscriptionDto.getDisciplineId(), null, null);
        return new Subscription(discipline, subscriptionDto.getEndDate(), subscriptionDto.getPrice(), subscriptionDto.getWorkoutCount());
    }

    /**
     * Метод entityInDto выполняет преобразование объекта Subscription в объект SubscriptionDto.
     *
     * @param subscription объект Subscription
     * @return объект SubscriptionDto
     */
    public static SubscriptionDto entityInDto(Subscription subscription) {
        Long disciplineId = subscription.getDiscipline() != null ? subscription.getDiscipline().getId() : null;
        return new SubscriptionDto(subscription.getId(), disciplineId, subscription.getWorkoutCount(), subscription.getEndDate(), subscription.getPrice());
    }
}
