package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.scim.user.UserDetailsFromAdmin;
import com.example.demo.scim.user.UserDetailsFromUser;
import com.example.demo.scim.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("username not found"));
    }

    public User getSignedInUser(@AuthenticationPrincipal final User user) {
        return user;
    }

//    public List<UserDetailsFromUser> getUsersFromUser() {
//        return userRepository.getAllUsersInvokedByUser();
//    }
//    public List<UserDetailsFromAdmin> getUsersFromAdmin() {
//        return userRepository.getAllUsersInvokedByAdmin();
//    }
    public List<Object> getUsers(@AuthenticationPrincipal final User user) {
        if (user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("USER"))) {
            List<UserDetailsFromUser> userDetailsFromUsers = userRepository.getAllUsersInvokedByUser();
            return Collections.singletonList(userDetailsFromUsers);
        } else {
            List<UserDetailsFromAdmin> userDetailsFromAdmins = userRepository.getAllUsersInvokedByAdmin();
            return Collections.singletonList(userDetailsFromAdmins);
        }
    }

    public void signUpUser(User user){

//        boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
//
//        if (userExists) {
//            throw new IllegalStateException("Username already exists");
//        }

        // TODO: encode and set the password, save user

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public List<UserDetailsFromUser> findUsersInvokedByUser() {
        return userRepository.getAllUsersInvokedByUser();
    }

    public Object getUserById(@AuthenticationPrincipal final User user, Long id) {
        if (user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("USER"))) {
            Optional<UserDetailsFromUser> userDetailsFromUser = userRepository.findUserByIdInvokedByUser(id);
            if (userDetailsFromUser.isEmpty()) {
                throw new UsernameNotFoundException("User with ID: "+id+" not found");
            } else {
                return userDetailsFromUser;
            }
        } else {
            Optional<UserDetailsFromAdmin> userDetailsFromAdmin = userRepository.findUserByIdInvokedByAdmin(id);
            if (userDetailsFromAdmin.isEmpty()) {
                throw new UsernameNotFoundException("User with ID: "+id+" not found");
            } else {
                return userDetailsFromAdmin;
            }
        }
    }

    @Transactional
    public String deleteUserByID(@AuthenticationPrincipal final User user, Long id) {
        if (user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN"))) {
            String status = "Deleted";
            Optional<Object> statusDeleted = userRepository.deleteUserByID(id);
            if (statusDeleted.isEmpty()) {
                throw new UsernameNotFoundException("User with ID: "+id+" not found");
            } else {
                return status;
            }
        } else {
            throw new AuthorizationServiceException("Unauthorized");
        }
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
//    public Optional<User> findUserByUsername(String username){
//        return userRepository.findByUsername(username);
//    };
}
