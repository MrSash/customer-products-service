package ru.mrsash.testtask.appsmart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsash.testtask.appsmart.dto.ProductDto;
import ru.mrsash.testtask.appsmart.dto.ProductUpdateDto;
import ru.mrsash.testtask.appsmart.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable("productId") UUID productId) {
        return productService.getProduct(productId);
    }

    @PutMapping("/{productId}")
    public void updateCustomer(
            @PathVariable("productId") UUID productId,
            @RequestBody ProductUpdateDto productUpdateDto
    ) {
        productService.updateProduct(productId, productUpdateDto);
    }

    @DeleteMapping("/{productId}")
    public void updateCustomer(@PathVariable("productId") UUID productId) {
        productService.deleteProduct(productId);
    }
}
