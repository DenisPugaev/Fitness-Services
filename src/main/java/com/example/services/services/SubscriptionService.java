package com.example.services.services;


import com.example.services.converters.SubscriptionConverter;
import com.example.services.dto.SubscriptionRequest;
import com.example.services.dto.SubscriptionResponse;
import com.example.services.entities.Discipline;
import com.example.services.entities.Subscription;
import com.example.services.exceptions.ResourceNotFoundException;

import com.example.services.repository.SubscriptionRepository;
import com.example.services.repository.specifications.SubscriptionSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;
/**
Полностью переделать
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;
    private final DisciplineService disciplineService;
    private final SubscriptionConverter subscriptionConverter;


    public Page<SubscriptionResponse> findAll(BigDecimal minPrice, BigDecimal maxPrice, String titlePart, Integer page) {
        Specification<Subscription> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(SubscriptionSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(SubscriptionSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
       
        log.info(subscriptionRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id"))).toString());

        return subscriptionRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id")))
                .map(subscriptionConverter::subscriptionToResponse);
    }


    public Optional<SubscriptionResponse> findById(Long id) throws ResourceNotFoundException {
        return subscriptionRepository.findById(id)
                .map(subscriptionConverter::subscriptionToResponse);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public void deleteById(Long id) {
        subscriptionRepository.deleteById(id);
    }


    @Transactional
    public SubscriptionResponse update(Long subId, Long disciplineId,Integer workoutCount, Integer daysToExpire, BigDecimal price) {
        Subscription sub = subscriptionRepository.findById(subId).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить услугу! ID:" + subId + " не найден!"));
        if(disciplineId!=null) sub.setDiscipline(disciplineService.findById(disciplineId).get());
        if(workoutCount!=null) sub.setWorkoutCount(workoutCount);
        if(daysToExpire!=null) sub.setDaysToExpire(daysToExpire);
        if(price!=null) sub.setPrice(price);
        subscriptionRepository.save(sub);
        return subscriptionConverter.subscriptionToResponse(sub);
    }

    public SubscriptionResponse addSubscription(Long subId, Long disciplineId,Integer workoutCount, Integer daysToExpire, BigDecimal price) {
        Subscription subscription = new Subscription();
        Optional<Discipline> discipline =disciplineService.findById(disciplineId);
       // subscription.setDiscipline(discipline); в сущность пытаешься подставить опционал
        subscription.setDiscipline(discipline.get());
        subscription.setWorkoutCount(workoutCount);
        subscription.setDaysToExpire(daysToExpire);
        subscription.setPrice(price);
        subscriptionRepository.save(subscription);
        return subscriptionConverter.subscriptionToResponse(subscription);
    }
}
