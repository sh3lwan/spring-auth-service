package org.sh3lwan.authservice.dto;

import lombok.Builder;
import org.sh3lwan.authservice.model.User;
import java.util.List;

@Builder
public class UserResponse {
    private String message;
    private List<User> users;
}
