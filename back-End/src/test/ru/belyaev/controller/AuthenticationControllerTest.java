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
import ru.belyaev.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class AuthenticationControllerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;


    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();

    }

    @Test
    public void controllerIsCreated() throws Exception {
        assertThat(wac.getBean("authenticationController")).isNotNull();
    }

    @Test
    public void shouldReturnLoginView() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(view().name("loginPage"));
    }

    @Test
    public void shouldReturnRegistrationView() throws Exception {
            this.mockMvc.perform(get("/registration"))
                    .andDo(print())
                    .andExpect(view().name("registrationForm"));
    }

    @Test
    public void shouldAddUserToDB() throws Exception {
        this.mockMvc.perform(post("/registration"))
                .andDo(print())
                .andExpect(view().name("loginPage"));
    }


}