package ru.belyaev.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.SpringCacheBasedAclCache;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.config.ApplicationConfig;
import ru.belyaev.entity.Product;
import ru.belyaev.service.ProductService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.testng.Assert.*;


@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class SearchTextControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @InjectMocks
    private SearchTextController searchTextController;

    @Mock
    private ProductService productService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(searchTextController).build();
    }

    @Test
    public void controllerIsCreated() {
        assertThat(wac.getBean("searchTextController")).isNotNull();
    }

    @Test
    public void checkViewName() throws Exception {
        mockMvc.perform(get("/searchProduct")
                     .param("fragment", "hemn"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }
}