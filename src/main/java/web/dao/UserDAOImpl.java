package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

//    @Autowired
//    private EntityManager entityManager;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        Query query = entityManagerFactory.getObject().createEntityManager().createQuery("from User");
        return query.getResultList();
    }
}
