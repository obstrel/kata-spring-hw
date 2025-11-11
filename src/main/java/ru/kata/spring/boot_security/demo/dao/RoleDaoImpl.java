package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        try {
            TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("FROM Role", Role.class);
        return query.getResultList();
    }

    @Override
    public List<Role> getDefaultRoles() {
        return List.of(
                new Role(Role.ROLE_ADMIN)
                , new Role(Role.ROLE_USER)
        );
    }

    @Override
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

}
