package com.example.services.converters;

import com.example.services.dto.SubscriptionResponse;
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
     * @param subscriptionRequest объект SubscriptionDto
     * @return объект Subscription
     */
/*    public  Subscription dtoInEntity(SubscriptionRequest subscriptionRequest) {
        // Проверить и исправить null
        Discipline discipline = new Discipline(null, null, null);
        return new Subscription(discipline, subscriptionRequest.getEndDate(), subscriptionRequest.getPrice(), subscriptionRequest.getWorkoutCount());
    }*/

    /**
     * Метод entityInDto выполняет преобразование объекта Subscription в объект SubscriptionResponse.
     *
     * @param subscription объект Subscription
     * @return объект SubscriptionResponse
     */
    public SubscriptionResponse subscriptionToResponse(Subscription subscription){
        return SubscriptionResponse.builder()
                .id(subscription.getId())
                .discipline(subscription.getDiscipline().getName())
                .workoutCount(subscription.getWorkoutCount())
                .daysToExpire(subscription.getDaysToExpire())
                .price(subscription.getPrice())
                .build();

    }

    public SubscriptionResponse subscriptionToRequest(Subscription subscription){
        return SubscriptionResponse.builder()
                .discipline(subscription.getDiscipline().getName())
                .workoutCount(subscription.getWorkoutCount())
                .daysToExpire(subscription.getDaysToExpire())
                .price(subscription.getPrice())
                .build();

    }
}
