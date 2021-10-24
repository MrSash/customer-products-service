package ru.mrsash.testtask.appsmart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mrsash.testtask.appsmart.dto.ProductCreateDto;
import ru.mrsash.testtask.appsmart.dto.ProductDto;
import ru.mrsash.testtask.appsmart.dto.ProductUpdateDto;

import java.util.UUID;

public interface ProductService {

    ProductDto getProduct(UUID productId);

    void updateProduct(UUID productId, ProductUpdateDto productUpdateDto);

    void deleteProduct(UUID productId);

    Page<ProductDto> getProducts(UUID customerId, Pageable pageable);

    void createProduct(UUID customerId, ProductCreateDto productCreateDto);
}
