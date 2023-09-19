package com.example.services.services;

import com.example.services.entities.Discipline;
import com.example.services.repository.DisciplineRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public Optional<Discipline> findById(Long id){
        return disciplineRepository.findById(id);
    }

    public Optional<Discipline> findByName(String name){
        return disciplineRepository.findByName(name);
    }

}
