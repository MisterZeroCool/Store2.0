package ru.alex.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
