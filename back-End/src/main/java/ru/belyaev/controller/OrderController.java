package ru.belyaev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.belyaev.entity.Order;
import ru.belyaev.entity.User;
import ru.belyaev.service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/api/orders/{user}")
    public List<Order> showUserOrders(@PathVariable User user) {
        List<Order> orders = orderService.findUserOrders(user);
        return orders;
    }

    @GetMapping("/api/orders/{orderId}")
    public Order showOneUserOrder(@PathVariable long orderId) {
        Order order = orderService.findOrderById(orderId);
//        if (order == null) throw new ResourceNotFoundException("something wrong");
        return order;
    }


}
