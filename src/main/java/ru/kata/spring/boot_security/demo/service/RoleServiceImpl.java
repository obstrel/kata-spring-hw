package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.dao.RoleDao;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDAO) {
        this.roleDao = roleDAO;
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role findByName(String roleName) {
        Role role = roleDao.getRoleByName(roleName);

        if(role == null) {
            role = new Role(roleName);
            roleDao.saveRole(role);
        }

        return role;
    }
}
