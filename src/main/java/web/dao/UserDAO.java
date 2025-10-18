package web.dao;

import web.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
}
