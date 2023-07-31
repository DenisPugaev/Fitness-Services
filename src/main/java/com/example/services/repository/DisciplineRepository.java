package com.example.services.repository;

import com.example.services.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью "Дисциплина".
 */
@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

}