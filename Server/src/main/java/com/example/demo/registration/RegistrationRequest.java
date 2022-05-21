package com.example.demo.registration;

import java.util.Objects;


public class RegistrationRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;


    public RegistrationRequest(){
        super();
    }

    public RegistrationRequest(String username, String password, String lastName, String email, String firstName) {
        this.username = username;
        this.password = password;
        this.lastName = lastName;
        this.email = email;
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) && password.equals(that.password) && lastName.equals(that.lastName) && email.equals(that.email) && firstName.equals(that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, lastName, email, firstName);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}


