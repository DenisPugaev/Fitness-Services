package com.example.services.repository;

import com.example.services.entities.Subscription; import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.data.jpa.repository.JpaSpecificationExecutor; import org.springframework.stereotype.Repository;

/**

 Репозиторий для работы с сущностью "Подписка". */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{
    //, JpaSpecificationExecutor<Subscription> {
}