package com.example.demo.scim.user;

import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.Length;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void findUserByIdInvokedByUser() {
        Optional<UserDetailsFromUser> response = underTest.findUserByIdInvokedByUser((long) 1);

        // Test user at id 1 exists
        boolean present = response.isPresent();
        assertTrue(present);

        Optional<UserDetailsFromUser> responseEmpty = underTest.findUserByIdInvokedByUser((long) 5);
        // Test user at id 5 doesn't exist
        boolean nonExistent = responseEmpty.isPresent();
        assertFalse(nonExistent);

        // test that the first users first name is Bob
        String name = response.get().getFirstName();
        assertEquals(name, "Bob");

        // test to see that output is only 4 fields
        int numberOfVariables = response.get().getLENGTH();
        assertEquals(numberOfVariables, 4);
    }

    @Test
    void findUserByIdInvokedByAdmin() {
        Optional<UserDetailsFromAdmin> response = underTest.findUserByIdInvokedByAdmin(underTest.findAll().get(0).getId());
        // Test user at id 1 exists
        boolean present = response.isPresent();
        assertTrue(present);

        Optional<UserDetailsFromAdmin> responseEmpty = underTest.findUserByIdInvokedByAdmin((long) 5);
        // Test user at id 5 doesn't exist
        boolean nonExistent = responseEmpty.isPresent();
        assertFalse(nonExistent);

        // test that the first users first name is Bob
        String name = response.get().getFirstName();
        assertEquals(name, "Bob");

        // test to see that output is only 4 fields
        int numberOfVariables = response.get().getLENGTH();
        assertEquals(numberOfVariables, 9);
    }

    @Test
    void getAllUsersInvokedByUser() {
        List<UserDetailsFromUser> response = underTest.getAllUsersInvokedByUser();

        // Test if length of the list is 2
        int sizeOfList = response.size();
        assertEquals(sizeOfList, 2);

        // test that the first users first name is Bob
        String name = response.get(0).getFirstName();
        assertEquals(name, "Bob");

        // test to see that output is only 4 fields
        int numberOfVariables = response.get(0).getLENGTH();
        assertEquals(numberOfVariables, 4);
    }

    @Test
    void getAllUsersInvokedByAdmin() {
        List<UserDetailsFromAdmin> response = underTest.getAllUsersInvokedByAdmin();

        // Test if length of the list is 2
        int sizeOfList = response.size();
        assertEquals(sizeOfList, 2);

        // test that the first users first name is Bob
        String name = response.get(0).getFirstName();
        assertEquals(name, "Bob");

        // test to see that output is only 4 fields
        int numberOfVariables = response.get(0).getLENGTH();
        assertEquals(numberOfVariables, 9);
    }



}