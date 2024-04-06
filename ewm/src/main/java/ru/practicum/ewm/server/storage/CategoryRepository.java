package ru.practicum.ewm.server.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.server.models.category.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}