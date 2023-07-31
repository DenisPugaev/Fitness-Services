package com.example.services.repository.specifications;

import com.example.services.entities.Subscription; import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class SubscriptionSpecifications {
    public static Specification priceGreaterOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Subscription> priceLessThanOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Subscription> disciplineLike(String disciplineName) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("discipline").get("name"), String.format("%%%s%%", disciplineName));
    }
}