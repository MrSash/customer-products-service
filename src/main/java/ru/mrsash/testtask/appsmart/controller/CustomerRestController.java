package ru.mrsash.testtask.appsmart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mrsash.testtask.appsmart.dto.CustomerCreateDto;
import ru.mrsash.testtask.appsmart.dto.CustomerDto;
import ru.mrsash.testtask.appsmart.dto.CustomerUpdateDto;
import ru.mrsash.testtask.appsmart.dto.ProductCreateDto;
import ru.mrsash.testtask.appsmart.dto.ProductDto;
import ru.mrsash.testtask.appsmart.service.CustomerService;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public CustomerDto getCustomers(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping
    public Page<CustomerDto> getCustomers(Pageable pageable) {
        return customerService.getCustomers(pageable);
    }

    @PostMapping
    public void createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        customerService.createCustomer(customerCreateDto);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") UUID customerId,
            @RequestBody CustomerUpdateDto customerUpdateDto
    ) {
        customerService.updateCustomer(customerId, customerUpdateDto);
    }

    @DeleteMapping("/{customerId}")
    public void updateCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/{customerId}/products")
    public Page<ProductDto> getCustomerProducts(@PathVariable("customerId") UUID customerId, Pageable pageable) {
        return customerService.getCustomerProducts(customerId, pageable);
    }

    @PostMapping("/{customerId}/products")
    public void getCustomerProducts(
            @PathVariable("customerId") UUID customerId,
            ProductCreateDto productCreateDto
    ) {
        customerService.createCustomerProduct(customerId, productCreateDto);
    }
}
