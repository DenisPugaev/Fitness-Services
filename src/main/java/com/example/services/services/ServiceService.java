package com.example.services.services;


import com.example.services.dto.ServiceDto;
import com.example.services.entities.Service;
import com.example.services.exceptions.ResourceNotFoundException;
import com.example.services.repository.ServiceRepository;
import com.example.services.repository.specifications.ServiceSpecifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public Page<Service> findAll(BigDecimal minPrice, BigDecimal maxPrice, String titlePart, Integer page) {
        Specification<Service> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ServiceSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ServiceSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (titlePart != null) {
            spec = spec.and(ServiceSpecifications.nameLike(titlePart));
        }

        log.info(serviceRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id"))).toString());


        return serviceRepository.findAll(spec, PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.ASC, "Id")));
    }


    public Optional<Service> findById(Long id) {
        return serviceRepository.findById(id);
    }

    public Service save(Service product) {
        return serviceRepository.save(product);
    }

    public void deleteById(Long id) {
        serviceRepository.deleteById(id);
    }


    @Transactional
    public Service update(ServiceDto serviceDto) {
        Service service = serviceRepository.findById(serviceDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить услугу! ID:" + serviceDto.getId() + " не найден!"));
        service.setTitle(serviceDto.getTitle());
        service.setPrice(serviceDto.getPrice());
        service.setDescription(service.getDescription());
        return service;
    }

    public Service addService(ServiceDto serviceDto) {
        Service service = new Service();
        service.setTitle(serviceDto.getTitle());
        service.setPrice(serviceDto.getPrice());
        service.setDescription(serviceDto.getDescription());
        return serviceRepository.save(service);
    }
}
