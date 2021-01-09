package ru.belyaev.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.config.ApplicationConfig;
import ru.belyaev.entity.*;
import ru.belyaev.exception.InternalServerErrorException;
import ru.belyaev.repository.OrderRepository;
import ru.belyaev.repository.ShoppingCartRepository;

import java.util.HashSet;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class OrderServiceImplTest extends AbstractTestNGSpringContextTests {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private Address address;

    @Mock
    private OrderRepository orderRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = InternalServerErrorException.class)
    public void shouldThrowExceptionWhenShoppingCartIsNull() {
        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();
        when(shoppingCartRepository.findShoppingCartByUser(user)).thenReturn(null);
        orderService.makeOrder(shoppingCart, user, address);
    }

    @Test
    public void testMakeOrder() {
        User user = new User();
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setProduct(new Product());
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setCount(2);
        HashSet<ShoppingCartItem> list = new HashSet();
        list.add(shoppingCartItem);
        shoppingCart.setShoppingCartItems(list);
        orderService.makeOrder(shoppingCart, user, address);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(shoppingCartRepository, times(1)).delete(shoppingCart);
    }
}