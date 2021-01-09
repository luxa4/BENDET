package ru.belyaev.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.belyaev.entity.User;
import ru.belyaev.repository.RoleRepository;
import ru.belyaev.repository.UserRepository;

import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        userService.addUser(user);
        verify(userRepository, times(1)).save(any(User.class));
    }

}