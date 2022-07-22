package ru.alex.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
