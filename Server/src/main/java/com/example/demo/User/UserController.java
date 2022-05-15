package com.example.demo.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @GetMapping
    public List<User> getUsers() {
        return List.of(
                new User(
                        "htemuri",
                        "test123",
                        "harris",
                        "harris.temuri@gmail.com"
                )
        );
    }
}
