package ru.mrsash.testtask.appsmart.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateDto {

    private String title;

    private String description;

    private BigDecimal price;
}
