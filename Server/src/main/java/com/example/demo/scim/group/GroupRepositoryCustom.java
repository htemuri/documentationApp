package com.example.demo.scim.group;

import com.example.demo.entities.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GroupRepositoryCustom {
    @Query("SELECT u FROM Group u WHERE u.id = :id")
    Optional<Group> getGroupById(@Param("id") Long id);
    @Query("DELETE FROM Group u WHERE u.id = :id")
    @Modifying
    Optional<Object> deleteUserById(@Param("id") Long id);
}
