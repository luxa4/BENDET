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
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.config.ApplicationConfig;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;
import ru.belyaev.service.ShoppingCartService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ShoppingCartControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private User user;

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }

    @Test
    public void controllerIsCreated() {
        Assert.assertNotNull(wac.getBean("shoppingCartController"));
    }

    @Test
    public void shouldReturnViewShoppingCart() throws Exception {
        when(shoppingCartService.getShoppingCartByUser(user)).thenReturn(new ShoppingCart());
        this.mockMvc.perform(get("/shoppingCart"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("shoppingCartPage"));
    }
}