package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
