package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> findAll();
    User getUserById(Long userId);
    Object deleteUserById(Long userId);
}
