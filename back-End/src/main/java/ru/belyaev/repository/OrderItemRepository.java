package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
