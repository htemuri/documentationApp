package com.example.demo.scim.user;

import java.util.Objects;

public class UserDetailsFromAdmin {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private boolean isAdmin;
    private boolean locked;
    private boolean enabled;

    public UserDetailsFromAdmin(Long id,
                                String username,
                                String firstName,
                                String lastName,
                                String email,
                                UserRole userRole,
                                boolean isAdmin,
                                boolean locked,
                                boolean enabled) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
        this.isAdmin = isAdmin;
        this.locked = locked;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsFromAdmin that = (UserDetailsFromAdmin) o;
        return isAdmin == that.isAdmin && locked == that.locked && enabled == that.enabled && id.equals(that.id) && username.equals(that.username)  && firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && userRole == that.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, userRole, isAdmin, locked, enabled);
    }

    @Override
    public String toString() {
        return "UserDetailsFromAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                ", isAdmin=" + isAdmin +
                ", locked=" + locked +
                ", enabled=" + enabled +
                '}';
    }
}
