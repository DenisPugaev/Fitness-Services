package com.example.services.controllers;





import com.example.services.dto.SubscriptionRequest;
import com.example.services.dto.SubscriptionResponse;
import com.example.services.entities.Subscription;
import com.example.services.exceptions.ResourceNotFoundException;
import com.example.services.services.SubscriptionService;
import com.example.services.validators.ServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {


    private final SubscriptionService subscriptionService;

    private final ServiceValidator serviceValidator;

//    http://localhost:8080/fitness-service/api/v1/service?page=1
    @GetMapping
    public Page<SubscriptionResponse> findAllService(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        log.debug(String.format("%nLogParam - Page: %s%n minPrice: %f%n maxPrice: %f%n namePart: %s%n", page, minPrice, maxPrice, titlePart));
        if (page < 1) {
            page = 1;
        }
        return subscriptionService.findAll(minPrice, maxPrice, titlePart, page);
    }

    @GetMapping("/{id}")
    public SubscriptionResponse findServiceById(@PathVariable("id") Long id) {
       return subscriptionService.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Service not found, id: " + id));

    }


//С фронта поэтому лучше Request param
    @PostMapping("/update")
    public SubscriptionResponse updateService(
            @RequestParam(name="subId") Long subId,
            @RequestParam(name ="discId",required = false )Long disciplineId,
            @RequestParam(name ="workCount",required = false )Integer workoutCount,
            @RequestParam(name ="daysExp",required = false)Integer daysToExpire,
            @RequestParam(name ="price",required = false)BigDecimal price
    ) {
        //serviceValidator.validate(subscriptionRequest);
        return  subscriptionService.update(subId,disciplineId,workoutCount,daysToExpire,price);

    }

    @PostMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        subscriptionService.deleteById(id);
    }

    @PostMapping("/add")
    public SubscriptionResponse addService(
            @RequestParam(name="subId") Long subId,
            @RequestParam(name ="discId")Long disciplineId,
            @RequestParam(name ="workCount")Integer workoutCount,
            @RequestParam(name ="daysExp")Integer daysToExpire,
            @RequestParam(name ="price")BigDecimal price
    ) {
//        serviceValidator.validate(subscriptionDto);
//        Subscription subscription = subscriptionConverter.dtoInEntity(subscriptionDto);
        return  subscriptionService.addSubscription(subId,disciplineId,workoutCount,daysToExpire,price);

    }

    @PostMapping("/buy/{id}")
    public void makeABuy(@RequestHeader(name = "login")String login, @PathVariable(name = "id")Long id){
        subscriptionService.makeABuy(login,id);
    }




}
