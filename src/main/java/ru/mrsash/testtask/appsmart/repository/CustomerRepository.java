package ru.mrsash.testtask.appsmart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrsash.testtask.appsmart.entity.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Page<Customer> findAllByDeletedIsFalse(Pageable pageable);

    Optional<Customer> findByIdAndDeletedFalse(UUID customerId);
}
