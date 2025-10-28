package springboot.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.UserDAO;
import springboot.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
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
}
