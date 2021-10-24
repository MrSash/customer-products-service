package ru.mrsash.testtask.appsmart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.mrsash.testtask.appsmart.entity.Customer;

import java.util.UUID;

public interface CustomerService {

    Customer getCustomer(UUID customerId);

    Page<Customer> getCustomers(Pageable pageable);
}
