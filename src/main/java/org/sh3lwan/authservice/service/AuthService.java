package org.sh3lwan.authservice.service;

import org.sh3lwan.authservice.dto.AuthRequest;
import org.sh3lwan.authservice.dto.RegisterRequest;
import org.sh3lwan.authservice.model.User;
import org.sh3lwan.authservice.repository.UserRepository;
import org.sh3lwan.authservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(RegisterRequest registerRequest) throws InstanceAlreadyExistsException {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new InstanceAlreadyExistsException("Username is already in use");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);

        return jwtUtil.generateToken(user.getUsername());
    }

    public String loginUser(AuthRequest request) throws RuntimeException, IllegalArgumentException {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
