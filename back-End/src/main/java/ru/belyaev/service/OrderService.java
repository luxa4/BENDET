/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 15:11
 */

package ru.belyaev.service;

import ru.belyaev.entity.Address;
import ru.belyaev.entity.Order;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> findUserOrders(User user);

    long findCountUserOrders(User user);

    Order findOrderById(long id);

    Order makeOrder(ShoppingCart shoppingCart, User user, Address address);

    boolean existOrdersByUser(User user);
}
