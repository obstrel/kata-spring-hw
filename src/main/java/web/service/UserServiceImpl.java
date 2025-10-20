package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDAO.findUserById(id);
    }

    @Override
    public void removeUserById(Long id) {
        userDAO.removeUserById(id);
    }
}
