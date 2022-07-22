package ru.alex.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.persist.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
