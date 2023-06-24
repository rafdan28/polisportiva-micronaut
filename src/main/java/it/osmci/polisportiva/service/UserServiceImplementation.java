package it.osmci.polisportiva.service;

import it.osmci.polisportiva.altro.exception.ResourceNotFoundException;
import it.osmci.polisportiva.model.User;
import it.osmci.polisportiva.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class UserServiceImplementation implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        Objects.requireNonNull(user);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        if(userList.size() != 0) return userList;
        else throw new ResourceNotFoundException("There are no users present.");
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("This user id doesn't identify any user!"));
    }

    @Override
    public Object deleteUserById(Long userId) {
        Objects.requireNonNull(userId);
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return "This user id has been deleted";
        }
        else throw new ResourceNotFoundException("This user id doesn't identify any user!");
    }
}
