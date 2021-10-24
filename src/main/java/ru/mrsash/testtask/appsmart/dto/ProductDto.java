package ru.mrsash.testtask.appsmart.dto;

import lombok.Data;
import ru.mrsash.testtask.appsmart.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;

    private UUID customerId;

    private String title;

    private String description;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.customerId = product.getCustomerId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.createdAt = product.getCreatedAt();
        this.modifiedAt = product.getModifiedAt();
    }
}
