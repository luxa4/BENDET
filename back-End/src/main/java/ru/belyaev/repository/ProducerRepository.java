package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.Category;
import ru.belyaev.entity.Producer;
import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    @Override
    List<Producer> findAll();

    @Query("SELECT p.producer FROM Product p WHERE p.category=?1")
    List<Producer> findProducersByCategory(Category category);
}
