package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleDao;

public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDAO;

    public RoleServiceImpl(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findById(Long id) {
        return roleDAO.findById(id);
    }
}
