package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.Producer;
import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Integer> {

    @Override
    List<Producer> findAll();
}
