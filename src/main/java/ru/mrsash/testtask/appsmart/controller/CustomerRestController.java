package ru.mrsash.testtask.appsmart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Api(tags = "Customers")
@Tag(name = "Customers")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @ApiOperation(value = "Returns customer by id")
    @GetMapping("/{customerId}")
    public CustomerDto getCustomers(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomer(customerId);
    }

    @ApiOperation(value = "Returns paginated list of all customers")
    @GetMapping
    public Page<CustomerDto> getCustomers(Pageable pageable) {
        return customerService.getCustomers(pageable);
    }

    @ApiOperation(value = "Create new customer")
    @PostMapping
    public void createCustomer(@RequestBody CustomerCreateDto customerCreateDto) {
        customerService.createCustomer(customerCreateDto);
    }

    @ApiOperation(value = "Edit customer")
    @PutMapping("/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") UUID customerId,
            @RequestBody CustomerUpdateDto customerUpdateDto
    ) {
        customerService.updateCustomer(customerId, customerUpdateDto);
    }

    @ApiOperation(value = "Delete customer")
    @DeleteMapping("/{customerId}")
    public void updateCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

    @ApiOperation(value = "Returns paginated list of all customer products")
    @GetMapping("/{customerId}/products")
    public Page<ProductDto> getCustomerProducts(@PathVariable("customerId") UUID customerId, Pageable pageable) {
        return customerService.getCustomerProducts(customerId, pageable);
    }

    @ApiOperation(value = "Create new product for customer")
    @PostMapping("/{customerId}/products")
    public void getCustomerProducts(
            @PathVariable("customerId") UUID customerId,
            ProductCreateDto productCreateDto
    ) {
        customerService.createCustomerProduct(customerId, productCreateDto);
    }
}
