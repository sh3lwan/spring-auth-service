package org.sh3lwan.authservice.service;

import jakarta.persistence.EntityNotFoundException;
import org.sh3lwan.authservice.model.User;
import org.sh3lwan.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user.orElseThrow(EntityNotFoundException::new);
    }

    public List<User> loadAllUsers() {
        return userRepository.findAll();
    }
}
