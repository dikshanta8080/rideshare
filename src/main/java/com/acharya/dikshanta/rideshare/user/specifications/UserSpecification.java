package com.acharya.dikshanta.rideshare.user.specifications;

import com.acharya.dikshanta.rideshare.user.dto.request.UserFilterRequest;
import com.acharya.dikshanta.rideshare.user.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public static Specification<User> filterUsers(UserFilterRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.name() != null && !request.name().isBlank()) {
                predicates.add(cb.like(root.get("name"), "%" + request.name() + "%"));
            }
            if (request.gender() != null && !request.gender().isBlank()) {
                predicates.add(cb.equal(root.get("gender"), request.gender()));
            }
            if (request.role() != null && !request.role().isBlank()) {
                predicates.add(cb.equal(root.get("role"), request.role()));
            }
            if (request.minAge() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("age"), request.minAge()));
            }
            if (request.maxAge() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("age"), request.maxAge()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
