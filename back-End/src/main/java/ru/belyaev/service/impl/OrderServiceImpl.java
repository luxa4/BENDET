/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 15:11
 */


package ru.belyaev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belyaev.entity.*;
import ru.belyaev.exception.InternalServerErrorException;
import ru.belyaev.repository.OrderItemRepository;
import ru.belyaev.repository.OrderRepository;
import ru.belyaev.repository.ShoppingCartRepository;
import ru.belyaev.service.OrderService;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public Order makeOrder(ShoppingCart shoppingCart, User user, Address address) {
        if (shoppingCart == null || shoppingCart.getShoppingCartItems().isEmpty()) {
            throw new InternalServerErrorException("Shopping cart is null or empty");
        }
        try {
            Order order = new Order();
            order.setUser(user);
            order.setAddress(address);
            order.setCreated(new Timestamp(System.currentTimeMillis()));
            // добавление в базу элементов, созданного заказ
            Set<OrderItem> listOrderItems = toOrderItemParameterList(order, shoppingCart.getShoppingCartItems());
            order.setOrderItemsList(listOrderItems);
            order.setExecutionStatus("preparation for shipment");
            orderRepository.save(order);
            orderRepository.delete(order);
            shoppingCartRepository.delete(shoppingCart);
            return order;
        } catch (Exception e) {
            throw new InternalServerErrorException("Ошибка в добавлении заказа в Базу данных -> makeOrder");
        }
    }

    private Set<OrderItem> toOrderItemParameterList(Order order, Collection<ShoppingCartItem> items) {
        Set<OrderItem> parameterList = new HashSet<>();
        for (ShoppingCartItem item: items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setCount(item.getCount());
            parameterList.add(orderItem);
        }
        return parameterList;
    }

    @Override
    public List<Order> findUserOrders(User user) {
        return orderRepository.findAllByUser(user);
    }

    @Override
    public long findCountUserOrders(User user) {
        return orderRepository.countOrderByUser(user);
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findOrderById(id);
    }

    @Override
    public boolean existOrdersByUser(User user) {
        return orderRepository.findAllByUser(user).isEmpty() ? false : true;
    }
}
