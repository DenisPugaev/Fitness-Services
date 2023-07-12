package com.example.services.converters;



import com.example.services.dto.ServiceDto;
import com.example.services.entities.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverter {


    public Service dtoInEntity(ServiceDto serviceDto) {
        return new Service(serviceDto.getId(), serviceDto.getTitle(), serviceDto.getPrice(), serviceDto.getDescription());
    }

    public ServiceDto entityInDto(Service service) {
        return new ServiceDto(service.getId(), service.getTitle(), service.getPrice(), service.getDescription());
    }
}
