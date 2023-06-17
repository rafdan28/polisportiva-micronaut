package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Objects;

@Singleton
public class UserServiceImplementation implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("This user id doesn't identify any user!"));
    }

    @Override
    public User registerUser(User user) {
        Objects.requireNonNull(user);
        return userRepository.save(user);
    }
}
