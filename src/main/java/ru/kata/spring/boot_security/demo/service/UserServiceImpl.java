package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleDao;
import ru.kata.spring.boot_security.demo.repository.UserDao;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    public UserServiceImpl(UserDao userDAO, RoleDao roleDAO) {
        this.userDao = userDAO;
        this.roleDao = roleDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getRoles().isEmpty()) {
            Role defaultRole = roleDao.getRoleByName(Role.ROLE_USER);
            if (defaultRole == null) {
                defaultRole = new Role(Role.ROLE_USER);
                roleDao.saveRole(defaultRole);
            }
            user.addRole(defaultRole);
        }

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        userDao.saveUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = roleDao.getAllRoles();

        for (Role role : roleDao.getDefaultRoles()) {
            if (!allRoles.contains(role)) {
                allRoles.add(role);
            }
        }

        return allRoles;
    }

    @Override
    @Transactional
    public void generateDefaultUsers() {

        if (userDao.findUserByEmail("admin") == null) {
            User adminUser = new User("admin", "admin", "admin");
            adminUser.addRole(roleService.findByName(Role.ROLE_ADMIN));
            adminUser.addRole(roleService.findByName(Role.ROLE_USER));
            saveUser(adminUser);
        }

        if (userDao.findUserByEmail("user") == null) {
            User normalUser = new User("user", "user", "user");
            normalUser.addRole(roleService.findByName(Role.ROLE_USER));
            saveUser(normalUser);
        }

    }

}
