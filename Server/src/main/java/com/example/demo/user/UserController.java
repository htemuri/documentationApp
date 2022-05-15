package com.example.demo.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "user")
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(path = "user/{username}")
    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(userService.loadUserByUsername(username),HttpStatus.OK);
        } catch (
                UsernameNotFoundException e
        ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
