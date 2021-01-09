package ru.belyaev.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.config.ApplicationConfig;
import ru.belyaev.entity.Product;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;
import ru.belyaev.repository.ProductRepository;
import ru.belyaev.repository.ShoppingCartItemRepository;
import ru.belyaev.repository.ShoppingCartRepository;
import ru.belyaev.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private Product product;

    @Mock
    private ShoppingCart shoppingCart;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("sasha");
        user.setName("sasha");
        when(userRepository.findUserByName("sasha")).thenReturn(user);
    }


    @Test
    public void testAddToShoppingCart() {
        when(productRepository.findProductById(1)).thenReturn(product);
        when(shoppingCartRepository.findShoppingCartByUser(user)).thenReturn(shoppingCart);
        shoppingCart = shoppingCartService.addToShoppingCart(1,1, user);
        assertThat(shoppingCart).isNotNull();
        verify(productRepository, times(1)).findProductById(any(Integer.class));
        verify(shoppingCartRepository, times(3)).findShoppingCartByUser(any(User.class));
        verify(shoppingCartItemRepository, times(1)).findShoppingCartItemByShoppingCartAndProduct(any(ShoppingCart.class), any(Product.class));
    }

    @Test
    public void testRemoveFromShoppingCart() {
        when(productRepository.findProductById(1)).thenReturn(product);
        when(shoppingCartRepository.findShoppingCartByUser(user)).thenReturn(shoppingCart);
        shoppingCart = shoppingCartService.removeFromShoppingCart(1,1, user);
        assertThat(shoppingCart).isNotNull();
        verify(productRepository, times(1)).findProductById(any(Integer.class));
        verify(shoppingCartRepository, times(3)).findShoppingCartByUser(any(User.class));
        verify(shoppingCartItemRepository, times(1)).findShoppingCartItemByShoppingCartAndProduct(any(ShoppingCart.class), any(Product.class));
    }
}