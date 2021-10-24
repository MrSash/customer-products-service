package ru.mrsash.testtask.appsmart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mrsash.testtask.appsmart.entity.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByIdAndDeletedFalse(UUID customerId);

    Page<Product> findAllByCustomerIdAndDeletedIsFalse(UUID customerId, Pageable pageable);
}
