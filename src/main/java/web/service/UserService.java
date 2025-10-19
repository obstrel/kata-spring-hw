package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    static User createUser() {
        return new User();
    }

    List<User> getUsers();
    void saveUser(User user);

    User findUserById(Long id);

    void removeUserById(Long id);
}
