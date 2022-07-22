package ru.alex.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.persist.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
