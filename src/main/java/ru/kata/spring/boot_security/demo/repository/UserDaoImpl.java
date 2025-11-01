package ru.kata.spring.boot_security.demo.repository;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        if (user.getId() != null) {
            entityManager.merge(user);
        } else {
            entityManager.persist(user);
        }
    }

    @Override
    public User findUserById(Long id) {
        User user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public void removeUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }


}
