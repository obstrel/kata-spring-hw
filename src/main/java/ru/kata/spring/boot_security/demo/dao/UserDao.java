package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void saveUser(User user);

    User findUserById(Long id);

    void removeUserById(Long id);

    User findUserByEmail(String email);
}
