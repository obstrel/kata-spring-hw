package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    static User createUser() {
        return new User();
    }

    List<User> getUsers();

    void saveUser(User user);

    User findUserById(Long id);

    void removeUserById(Long id);

    public List<Role> getAllRoles();

    void generateDefaultUsers();
}
