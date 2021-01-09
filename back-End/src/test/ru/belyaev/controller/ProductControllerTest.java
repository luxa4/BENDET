package ru.belyaev.controller;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.config.ApplicationConfig;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;
import ru.belyaev.service.ProductService;
import ru.belyaev.service.ShoppingCartService;
import ru.belyaev.service.UserService;

import javax.servlet.ServletContext;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ProductControllerTest extends AbstractTestNGSpringContextTests {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private ServletContext servletContext;

    @Mock
    private ProductService productService;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private UserService userService;

    @Mock
    User user;

    @InjectMocks
    private ProductController productController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void controllerIsCreated() {
        assertThat(wac.getBean("productController")).isNotNull();
    }

    @Test
    public void shouldCallView() throws Exception {
        when(shoppingCartService.getShoppingCartByUser(user)).thenReturn(new ShoppingCart());
        when(productService.showMaxHeight()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMinHeight()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMaxLength()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMinLength()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMaxWidth()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMinWidth()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMaxPrice()).thenReturn(BigDecimal.valueOf(1));
        when(productService.showMinPrice()).thenReturn(BigDecimal.valueOf(1));

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}