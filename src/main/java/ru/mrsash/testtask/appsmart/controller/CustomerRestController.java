package ru.mrsash.testtask.appsmart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsash.testtask.appsmart.entity.Customer;
import ru.mrsash.testtask.appsmart.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public Customer getCustomers(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerService.getCustomers(pageable);
    }
}
