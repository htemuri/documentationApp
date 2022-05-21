package com.example.demo.scim.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    String nonSensitiveColumns = "u.id, u.username, u.firstName, u.lastName";
    String sensitiveColumns = "u.id, u.username,u.firstName,u.lastName,u.email,u.userRole,u.isAdmin,u.locked,u.enabled";
    @Query("SELECT new com.example.demo.scim.user.UserDetailsFromUser("+nonSensitiveColumns+") FROM User u WHERE id=?1")
    Optional<UserDetailsFromUser> findUserByIdInvokedByUser(Long id);
    @Query("SELECT new com.example.demo.scim.user.UserDetailsFromAdmin("+sensitiveColumns+") FROM User u WHERE id=?1")
    Optional<UserDetailsFromAdmin> findUserByIdInvokedByAdmin(Long id);
    @Query("SELECT new com.example.demo.scim.user.UserDetailsFromUser("+nonSensitiveColumns+") FROM User u")
    List<UserDetailsFromUser> getAllUsersInvokedByUser();
    @Query("SELECT new com.example.demo.scim.user.UserDetailsFromAdmin("+sensitiveColumns+") FROM User u")
    List<UserDetailsFromAdmin> getAllUsersInvokedByAdmin();
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    @Query("DELETE FROM User u WHERE u.id = :id")
    @Modifying
    public Optional<Object> deleteUserByID(@Param("id") Long id);
}
