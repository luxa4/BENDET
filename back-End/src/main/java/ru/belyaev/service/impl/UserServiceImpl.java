/*
 * Created by Vologda Developer
 * Date: 20.06.2020
 * Time: 13:57
 */


package ru.belyaev.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belyaev.entity.Role;
import ru.belyaev.entity.User;
import ru.belyaev.repository.RoleRepository;
import ru.belyaev.repository.UserRepository;
import ru.belyaev.service.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findUserByName(name);
        return user;
    }

    @Transactional
    @Override
    public void addUser(User userFromForm) {
        User user = new User();
        user.setName(userFromForm.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userFromForm.getPassword()));
        user.setEmail(userFromForm.getEmail());
        user.setRoles(Arrays.asList(roleRepository.findRoleByRole("ROLE_USER")));
        userRepository.save(user);
        System.out.println("Добавлен новый пользователь - " + user.getName());
        LOGGER.info("Добавлен новый пользователь");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(s);
        LOGGER.info("Was called method for authentication, parameters - {} ", user.getName());
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities (Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

}
