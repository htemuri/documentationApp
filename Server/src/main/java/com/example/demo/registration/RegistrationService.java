package com.example.demo.registration;

import com.example.demo.scim.user.User;
import com.example.demo.scim.user.UserRole;
import com.example.demo.scim.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        UserRole.CANVIEW,
                        false,
                        false,
                        true
                )
        );
        return "registered";
    }

}
