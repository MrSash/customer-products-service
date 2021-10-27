package ru.mrsash.testtask.appsmart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mrsash.testtask.appsmart.dto.CustomerCreateDto;
import ru.mrsash.testtask.appsmart.dto.CustomerDto;
import ru.mrsash.testtask.appsmart.dto.CustomerUpdateDto;
import ru.mrsash.testtask.appsmart.dto.ProductCreateDto;
import ru.mrsash.testtask.appsmart.dto.ProductDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustomer(UUID customerId);

    Page<CustomerDto> getCustomers(Pageable pageable);

    void createCustomer(CustomerCreateDto CustomerCreateDto);

    void updateCustomer(UUID customerId, CustomerUpdateDto customerUpdateDto);

    void deleteCustomer(UUID customerId);

    Page<ProductDto> getCustomerProducts(UUID customerId, Pageable pageable);

    void createCustomerProduct(UUID customerId, ProductCreateDto productCreateDto);
}
