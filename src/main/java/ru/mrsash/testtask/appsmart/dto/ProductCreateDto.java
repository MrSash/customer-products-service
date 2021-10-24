package ru.mrsash.testtask.appsmart.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductCreateDto {

    private String title;

    private String description;

    private BigDecimal price;
}
