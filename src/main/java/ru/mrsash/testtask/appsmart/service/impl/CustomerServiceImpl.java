package ru.mrsash.testtask.appsmart.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mrsash.testtask.appsmart.entity.Customer;
import ru.mrsash.testtask.appsmart.exception.CustomerNotFound;
import ru.mrsash.testtask.appsmart.repository.CustomerRepository;
import ru.mrsash.testtask.appsmart.service.CustomerService;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFound::new);
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
