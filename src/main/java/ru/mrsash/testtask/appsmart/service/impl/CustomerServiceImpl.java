package ru.mrsash.testtask.appsmart.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mrsash.testtask.appsmart.dto.CustomerCreateDto;
import ru.mrsash.testtask.appsmart.dto.CustomerDto;
import ru.mrsash.testtask.appsmart.dto.CustomerUpdateDto;
import ru.mrsash.testtask.appsmart.dto.ProductCreateDto;
import ru.mrsash.testtask.appsmart.dto.ProductDto;
import ru.mrsash.testtask.appsmart.entity.Customer;
import ru.mrsash.testtask.appsmart.exception.CustomerNotFound;
import ru.mrsash.testtask.appsmart.repository.CustomerRepository;
import ru.mrsash.testtask.appsmart.service.CustomerService;
import ru.mrsash.testtask.appsmart.service.ProductService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ProductService productService;

    @Override
    public CustomerDto getCustomer(UUID customerId) {
        log.info("Try receive customer with id = {}", customerId);
        final CustomerDto customerDto = new CustomerDto(getCustomerById(customerId));
        log.info("Customer successfully received (id = {})", customerId);
        return customerDto;
    }

    @Override
    public Page<CustomerDto> getCustomers(Pageable pageable) {
        log.info("Try receive customers with pagination (size = {}, page = {})",
                pageable.getPageSize(), pageable.getPageNumber());
        final Page<CustomerDto> customers = customerRepository.findAllByDeletedIsFalse(pageable).map(CustomerDto::new);
        log.info("Customers with pagination successfully received");
        return customers;
    }

    @Transactional
    @Override
    public void createCustomer(CustomerCreateDto customerCreateDto) {
        log.info("Try save customer (customer create dto = {})", customerCreateDto.toString());
        final Customer customer = customerRepository.save(Customer.builder()
                .title(customerCreateDto.getTitle())
                .build());
        log.info("Customer successfully saved (id = {})", customer.getId());
    }

    @Transactional
    @Override
    public void updateCustomer(UUID customerId, CustomerUpdateDto customerUpdateDto) {
        log.info("Try update customer with id = {} (customer update dto = {})",
                customerId, customerUpdateDto.toString());
        final Customer customer = getCustomerById(customerId);
        customer.setTitle(customerUpdateDto.getTitle());
        log.info("Customer successfully updated (id = {})", customer.getId());
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID customerId) {
        log.info("Try delete customer with id = {}", customerId);
        getCustomerById(customerId).setDeleted(true);
        log.info("Customer successfully deleted (id = {})", customerId);
    }

    @Override
    public Page<ProductDto> getCustomerProducts(UUID customerId, Pageable pageable) {
        return productService.getProducts(customerId, pageable);
    }

    @Override
    public void createCustomerProduct(UUID customerId, ProductCreateDto productCreateDto) {
        if (customerRepository.existsById(customerId)) {
            productService.createProduct(customerId, productCreateDto);
        } else {
            throw new CustomerNotFound();
        }
    }

    private Customer getCustomerById(UUID customerId) {
        return customerRepository.findByIdAndDeletedFalse(customerId).orElseThrow(CustomerNotFound::new);
    }
}
