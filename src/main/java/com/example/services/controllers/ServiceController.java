package com.example.services.controllers;



import com.example.services.converters.ServiceConverter;
import com.example.services.dto.ServiceDto;
import com.example.services.entities.Service;
import com.example.services.exceptions.ResourceNotFoundException;
import com.example.services.services.ServiceService;
import com.example.services.validators.ServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class ServiceController {


    private final ServiceService serviceService;
    private final ServiceConverter serviceConverter;
    private final ServiceValidator serviceValidator;


    @GetMapping
    public Page<ServiceDto> findAllService(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        log.debug(String.format("%nLogParam - Page: %s%n minPrice: %f%n maxPrice: %f%n namePart: %s%n", page, minPrice, maxPrice, titlePart));
        if (page < 1) {
            page = 1;
        }
        log.info(serviceService.findAll(minPrice, maxPrice, titlePart, page).map(serviceConverter::entityInDto).toString());
        return serviceService.findAll(minPrice, maxPrice, titlePart, page).map(serviceConverter::entityInDto);
    }

    @GetMapping("/{id}")
    public ServiceDto findServiceById(@PathVariable Long id) {
        Service service = serviceService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servce not found, id: " + id));
        return serviceConverter.entityInDto(service);
    }



    @PutMapping
    public ServiceDto updateService(@RequestBody ServiceDto serviceDto) {
        serviceValidator.validate(serviceDto);
        Service service = serviceService.update(serviceDto);
        return serviceConverter.entityInDto(service);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        serviceService.deleteById(id);
    }





}
