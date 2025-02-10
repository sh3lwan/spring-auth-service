package org.sh3lwan.authservice.controllers;

import org.sh3lwan.authservice.dto.UserResponse;
import org.sh3lwan.authservice.model.User;
import org.sh3lwan.authservice.repository.UserRepository;
import org.sh3lwan.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserResponse> index() {
        try {
            List<User> users = userService.loadAllUsers();
            return ResponseEntity.ok().body(UserResponse.builder().users(users).build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    UserResponse.builder()
                            .message("Something went wrong!")
                            .build()
            );
        }
    }
}
