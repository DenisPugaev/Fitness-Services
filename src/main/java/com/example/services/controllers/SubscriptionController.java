package com.example.services.controllers;





import com.example.services.converters.SubscriptionConverter;
import com.example.services.dto.SubscriptionDto;
import com.example.services.entities.Subscription;
import com.example.services.exceptions.ResourceNotFoundException;
import com.example.services.services.SubscriptionService;
import com.example.services.validators.ServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 Полностью переделать
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class SubscriptionController {


    private final SubscriptionService subscriptionService;
    private final SubscriptionConverter subscriptionConverter;
    private final ServiceValidator serviceValidator;

//    http://localhost:8080/fitness-service/api/v1/service?page=1
    @GetMapping
    public Page<SubscriptionDto> findAllService(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        log.debug(String.format("%nLogParam - Page: %s%n minPrice: %f%n maxPrice: %f%n namePart: %s%n", page, minPrice, maxPrice, titlePart));
        if (page < 1) {
            page = 1;
        }

        return subscriptionService.findAll(minPrice, maxPrice, titlePart, page).map(SubscriptionConverter::entityInDto);
    }

    @GetMapping("/{id}")
    public SubscriptionDto findServiceById(@PathVariable Long id) {
        Subscription subscription = subscriptionService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service not found, id: " + id));
        return SubscriptionConverter.entityInDto(subscription);
    }



    @PutMapping
    public SubscriptionDto updateService(@RequestBody SubscriptionDto subscriptionDto) {
        serviceValidator.validate(subscriptionDto);
        Subscription subscription = subscriptionService.update(subscriptionDto);
        return SubscriptionConverter.entityInDto(subscription);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        subscriptionService.deleteById(id);
    }


    @PostMapping
    public SubscriptionDto addService(@RequestBody SubscriptionDto subscriptionDto) {
        serviceValidator.validate(subscriptionDto);
        Subscription subscription = SubscriptionConverter.dtoInEntity(subscriptionDto);
        Subscription savedsubscription = subscriptionService.save(subscription);
        return SubscriptionConverter.entityInDto(savedsubscription);
    }



}
