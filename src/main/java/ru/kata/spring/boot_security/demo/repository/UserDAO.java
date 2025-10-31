package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    void saveUser(User user);

    User findUserById(Long id);

    void removeUserById(Long id);
}
