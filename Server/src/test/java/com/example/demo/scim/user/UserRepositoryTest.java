package com.example.demo.scim.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @BeforeEach
    void setUp() {
        List<Long> bobGroupIds = new ArrayList<>();
        List<Long> userGroupIds = List.of((long) 1324, (long) 3234);
        User bob = new User(
                "bob123",
                "password",
                "Bob",
                "Sagget",
                "bob.sag@gmail.com",
                UserRole.ADMIN,
                true,
                false,
                true,
                bobGroupIds
        );
        User user123 = new User(
                "user123",
                "password2",
                "User1",
                "LastName",
                "usersemail@gmail.com",
                UserRole.CANVIEW,
                false,
                false,
                true,
                userGroupIds
        );
        underTest.saveAll(List.of(bob,user123));
    }

    @Test
    @Disabled
    void findUserByIdInvokedByUser() {
    }

    @Test
    void findUserByIdInvokedByAdmin() {

    }

    @Test
    void getAllUsersInvokedByUser() {
        List<User> result = underTest.findAll();
        System.out.println();
    }

    @Test
    @Disabled
    void getAllUsersInvokedByAdmin() {
    }



}