package com.example.demo.scim.user;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    String nonSensitiveColumns = "u.id, u.username, u.firstName, u.lastName";
    String sensitiveColumns = "username,firstName,lastName,email,userRole,isAdmin,locked,enabled";
    @Query("SELECT "+nonSensitiveColumns+" FROM User u WHERE id='?0'")
    Optional<User> findUserByIdInvokedByUser(Long id);
    @Query("SELECT "+sensitiveColumns+" FROM User u WHERE id='?0'")
    Optional<User> findUserByIdInvokedByAdmin(Long id);
    @Query("SELECT "+nonSensitiveColumns+" FROM User u")
    List<Object> getAllUsersInvokedByUser();
    @Query("SELECT "+sensitiveColumns+" FROM User u")
    List<User> getAllUsersInvokedByAdmin();
}
