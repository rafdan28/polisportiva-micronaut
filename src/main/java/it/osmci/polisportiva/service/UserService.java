package it.osmci.polisportiva.service;

import io.micronaut.http.HttpResponse;
import it.osmci.polisportiva.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> findAll();
    User getUserById(Long userId);
    void deleteUserById(Long userId);
}
