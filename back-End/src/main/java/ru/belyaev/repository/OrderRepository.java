/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 15:10
 */

package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.Order;
import ru.belyaev.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);

    long countOrderByUser(User user);

    Order findOrderById(long id);
}
