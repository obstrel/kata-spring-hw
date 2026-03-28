package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public void assignRoles(User user, List<String> roleNames) {
        if (roleNames != null && !roleNames.isEmpty()) {
            Set<Role> roles = roleNames.stream()
                    .map(roleService::findByName)
                    .collect(Collectors.toSet());
            user.setRoles(roles);
        }
    }

    @Override
    public User findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (User) auth.getPrincipal();
    }

    @Override
    @Transactional
    public User updateUserFromRequestBody(Long userId, Map<String, Object> requestBody) {
        User user = findUserById(userId);


        if (requestBody.containsKey("firstName")) {
            user.setFirstName((String) requestBody.get("firstName"));
        }

        if (requestBody.containsKey("lastName")) {
            user.setLastName((String) requestBody.get("lastName"));
        }

        if (requestBody.containsKey("email")) {
            String newEmail = (String) requestBody.get("email");
            user.setEmail(newEmail);
        }


        if (requestBody.containsKey("roles")) {
            try {
                List<String> roleNames = (List<String>) requestBody.get("roles");
                assignRoles(user, roleNames);
            } catch (ClassCastException e) {
                return user;
            }
        }

        saveUser(user);

        return user;
    }

}
