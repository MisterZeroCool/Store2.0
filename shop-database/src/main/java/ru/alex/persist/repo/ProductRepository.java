package ru.alex.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.persist.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
