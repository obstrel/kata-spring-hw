package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = entityManager.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void saveUser(User user) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();

            transaction.begin();

            if (user.getId() != null) {
                entityManager.merge(user);
            } else {
                entityManager.persist(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error saving entity: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(Long id) {
        User user = entityManager.find(User.class, id);

        return user;
    }

    @Override
    public void removeUserById(Long id) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();

            User user = entityManager.find(User.class, id);

            transaction.begin();

            entityManager.remove(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error saving entity: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
