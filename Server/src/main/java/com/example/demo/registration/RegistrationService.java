package com.example.demo.registration;

import com.example.demo.user.User;
import com.example.demo.user.UserRole;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    public final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public String register(RegistrationRequest request) {
        userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword(),
                        UserRole.USER,
                        false,
                        false
                )
        );
        return "registered";

    }

}
