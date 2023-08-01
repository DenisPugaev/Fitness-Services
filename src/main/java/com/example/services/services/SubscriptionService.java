package com.example.services.services;


import com.example.services.dto.SubscriptionDto;
import com.example.services.entities.Subscription;
import com.example.services.exceptions.ResourceNotFoundException;

import com.example.services.repository.SubscriptionRepository;
import com.example.services.repository.specifications.SubscriptionSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
/**
Полностью переделать
 */
@Slf4j
@org.springframework.stereotype.Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;
    public SubscriptionService( SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

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
    public Subscription update(SubscriptionDto SubscriptionDto) {
        Subscription service = subscriptionRepository.findById(SubscriptionDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить услугу! ID:" + SubscriptionDto.getId() + " не найден!"));
//        service.setTitle(SubscriptionDto.getTitle());
        service.setPrice(SubscriptionDto.getPrice());
//        service.setDescription(service.getDescription());
        return service;
    }

    public Subscription addService(SubscriptionDto SubscriptionDto) {
        Subscription subscription = new Subscription();
//        subscription.setTitle(SubscriptionDto.getTitle());
        subscription.setPrice(SubscriptionDto.getPrice());
//        subscription.setDescription(SubscriptionDto.getDescription());
        return subscriptionRepository.save(subscription);
    }
}
