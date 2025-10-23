package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Transactional
    @Override
    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }
}
