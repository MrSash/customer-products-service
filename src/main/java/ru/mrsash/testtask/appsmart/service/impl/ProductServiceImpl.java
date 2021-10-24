package ru.mrsash.testtask.appsmart.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mrsash.testtask.appsmart.dto.ProductCreateDto;
import ru.mrsash.testtask.appsmart.dto.ProductDto;
import ru.mrsash.testtask.appsmart.dto.ProductUpdateDto;
import ru.mrsash.testtask.appsmart.entity.Product;
import ru.mrsash.testtask.appsmart.exception.ProductNotFound;
import ru.mrsash.testtask.appsmart.repository.ProductRepository;
import ru.mrsash.testtask.appsmart.service.ProductService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto getProduct(UUID productId) {
        log.info("Try receive product with id = {}", productId);
        final ProductDto productDto = new ProductDto(getProductById(productId));
        log.info("Product successfully received (id = {})", productId);
        return productDto;
    }

    @Transactional
    @Override
    public void updateProduct(UUID productId, ProductUpdateDto productUpdateDto) {
        log.info("Try update product with id = {} (product update dto = {})",
                productId, productUpdateDto.toString());
        final Product product = getProductById(productId);
        product.setTitle(productUpdateDto.getTitle());
        product.setDescription(productUpdateDto.getDescription());
        product.setPrice(productUpdateDto.getPrice());
        log.info("Product successfully updated (id = {})", product.getId());
    }

    @Transactional
    @Override
    public void deleteProduct(UUID productId) {
        log.info("Try delete product with id = {}", productId);
        getProductById(productId).setDeleted(true);
        log.info("Product successfully deleted (id = {})", productId);
    }

    @Override
    public Page<ProductDto> getProducts(UUID customerId, Pageable pageable) {
        log.info("Try receive products with pagination (size = {}, page = {})",
                pageable.getPageSize(), pageable.getPageNumber());
        final Page<ProductDto> products = productRepository
                .findAllByCustomerIdAndDeletedIsFalse(customerId, pageable)
                .map(ProductDto::new);
        log.info("Products with pagination successfully received");
        return products;
    }

    @Override
    public void createProduct(UUID customerId, ProductCreateDto productCreateDto) {
        log.info("Try save product (customer id = {}, customer create dto = {})",
                customerId, productCreateDto.toString());
        final Product product = productRepository.save(Product.builder()
                .customerId(customerId)
                .title(productCreateDto.getTitle())
                .description(productCreateDto.getDescription())
                .price(productCreateDto.getPrice())
                .build());
        log.info("Product successfully saved (id = {})", product.getId());
    }

    private Product getProductById(UUID productId) {
        return productRepository.findByIdAndDeletedFalse(productId).orElseThrow(ProductNotFound::new);
    }
}
