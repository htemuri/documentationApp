package com.example.demo.registration;

import java.util.Objects;


public class RegistrationRequest {
    private String username;
    private String password;
    private String name;
    private String email;

    public RegistrationRequest(){
        super();
    }

    public RegistrationRequest(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) && password.equals(that.password) && Objects.equals(name, that.name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, name, email);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
