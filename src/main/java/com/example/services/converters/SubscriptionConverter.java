package com.example.services.converters;

import com.example.services.dto.SubscriptionResponse;
import com.example.services.entities.Subscription;
import com.example.services.repository.DisciplineRepository;
import org.springframework.stereotype.Component;



@Component
public class SubscriptionConverter {
   private DisciplineRepository disciplineRepository;



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
