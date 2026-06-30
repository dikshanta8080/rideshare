package com.acharya.dikshanta.rideshare.common.dto.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PaginationRequest(
        int pageNo,
        int pageSize,
        String sortBy,
        String sortDirection
) {
    public PaginationRequest {
        pageSize = pageSize == 0 ? 10 : pageSize;
        if (sortBy == null || sortBy.isBlank()) {
            sortBy = "id";
        }

        if (sortDirection == null || sortDirection.isBlank()) {
            sortDirection = "ASC";
        } else {
            sortDirection = sortDirection.toUpperCase();
        }
    }

    public Pageable toPageable() {

        Sort.Direction direction;
        try {
            direction = Sort.Direction.valueOf(sortDirection);
        } catch (Exception e) {
            direction = Sort.Direction.ASC;
        }

        return PageRequest.of(
                pageNo,
                pageSize,
                Sort.by(direction, sortBy)
        );
    }
}
