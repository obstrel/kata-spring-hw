package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleDao;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDAO;
    private final RoleDao roleDAO;

    public UserServiceImpl(UserDao userDAO, RoleDao roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            Role defaultRole = roleDAO.getRoleByName(Role.ROLE_USER);
            if (defaultRole == null) {
                defaultRole = new Role(Role.ROLE_USER);
                roleDAO.saveRole(defaultRole);
            }
            user.addRole(defaultRole);
        }
        userDAO.saveUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }
}
