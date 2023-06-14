package it.osmci.polisportiva.service;

import it.osmci.polisportiva.model.User;

public interface UserService {
    User getUserById(Long userId);
    User registerUser(User user);
}
