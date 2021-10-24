package ru.mrsash.testtask.appsmart.dto;

import lombok.Data;
import ru.mrsash.testtask.appsmart.entity.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CustomerDto {

    private UUID id;

    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.title = customer.getTitle();
        this.createdAt = customer.getCreatedAt();
        this.modifiedAt = customer.getModifiedAt();
    }
}
