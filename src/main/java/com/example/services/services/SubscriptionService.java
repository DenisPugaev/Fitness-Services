package com.example.services.services;


import com.example.services.converters.SubscriptionConverter;
import com.example.services.dto.SubscriptionDto;
import com.example.services.entities.Discipline;
import com.example.services.entities.Subscription;
import com.example.services.exceptions.ResourceNotFoundException;

import com.example.services.exceptions.ValidationException;
import com.example.services.repository.DisciplineRepository;
import com.example.services.repository.SubscriptionRepository;
import com.example.services.repository.specifications.SubscriptionSpecifications;
import com.example.services.validators.ServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final DisciplineRepository disciplineRepository;
    private final ServiceValidator serviceValidator;
    private final SubscriptionConverter subscriptionConverter;




    public Page<Subscription> findAll(BigDecimal minPrice, BigDecimal maxPrice, String titlePart, Integer page) {
        Specification<Subscription> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(SubscriptionSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(SubscriptionSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
       
        log.info(subscriptionRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id"))).toString());


        return subscriptionRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id")));
    }


    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public void deleteById(Long id) {
        subscriptionRepository.deleteById(id);
    }


    @Transactional
    public SubscriptionDto update(Long subId,Long disciplineId,Integer workoutCount,Integer daysToExpire,BigDecimal price) {
        Subscription service = subscriptionRepository.findById(subId).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить услугу! ID:" + subId + " не найден!"));
//        service.setTitle(SubscriptionDto.getTitle());
        service.setPrice(price);
//        service.setDescription(service.getDescription());
        return subscriptionConverter.entityInDto(service);
    }

    public SubscriptionDto addSubscription(Long subId,Long disciplineId,Integer workoutCount,Integer daysToExpire,BigDecimal price) {
        Subscription subscription = new Subscription();
        Optional<Discipline> discipline = disciplineRepository.findById(disciplineId);
        Discipline resolvedDiscipline = discipline.orElseThrow(() -> new ResourceNotFoundException("Дисциплина ID:"+ disciplineId +" не найдена"));
        subscription.setDiscipline(resolvedDiscipline);
        subscription.setWorkoutCount(workoutCount);
        subscriptionRepository.save(subscription);

        return subscriptionConverter.entityInDto(subscription);
    }
}
