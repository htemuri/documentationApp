package com.example.demo.scim.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "me")
    public User getSignedInUser(@AuthenticationPrincipal final User user) {
        return user;
    }

    @GetMapping(path = "users")
    public List<Object> getUsersByUser(@AuthenticationPrincipal final User user) {
        return userService.getUsers(user);
    }


    @RequestMapping(method = RequestMethod.GET, path = "users/{id}")
    public Object getUserById(@AuthenticationPrincipal final User user, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(user, id),HttpStatus.OK);
        } catch (
                UsernameNotFoundException e
        ) {
            return e.getMessage();
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, path = "users/{id}")
    public Object deleteUserById(@AuthenticationPrincipal final User user, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.deleteUserByID(user, id),HttpStatus.OK);
        } catch (UsernameNotFoundException usernameNotFoundException){
            return usernameNotFoundException.getMessage();
        } catch (AuthorizationServiceException authorizationServiceException) {
            return HttpServletResponse.SC_UNAUTHORIZED;
        }
    }


//    @GetMapping(path = "user/{username}")
//    public ResponseEntity<UserDetails> getUserByUsername(@PathVariable String username) {
//        try {
//            return new ResponseEntity<>(userService.loadUserByUsername(username),HttpStatus.OK);
//        } catch (
//                UsernameNotFoundException e
//        ) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }
}
