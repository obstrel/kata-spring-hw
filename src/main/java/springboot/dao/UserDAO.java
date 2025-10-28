package springboot.dao;


import springboot.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    void saveUser(User user);

    User findUserById(Long id);

    void removeUserById(Long id);
}
