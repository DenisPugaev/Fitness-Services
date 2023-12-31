package com.example.services.repository;

import com.example.services.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для работы с сущностью "Дисциплина".
 */
@Repository

public interface DisciplineRepository extends JpaRepository<Discipline, Long>{

    Optional<Discipline> findByName(String name);

}