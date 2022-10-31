package com.example.demo.entities;

import com.example.demo.scim.user.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="users")
public class User implements UserDetails {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private boolean isAdmin;
    private boolean locked;
    private boolean enabled;
//    @ElementCollection(targetClass = Long.class)
//    private List<Long> groupId;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Set<Roles> roles = new HashSet<>();

    public User() {
    }


    public User(String username,
                String password,
                String firstName,
                String lastName,
                String email,
                UserRole userRole,
                boolean isAdmin,
                boolean locked,
                boolean enabled,
                List<Long> groupId) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
        this.isAdmin = isAdmin;
        this.locked = locked;
        this.enabled = enabled;
//        this.groupId = groupId;
    }

    public User(String username, String password, String firstName, String lastName, String email, UserRole userRole, boolean isAdmin, boolean locked, boolean enabled) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = userRole;
        this.isAdmin = isAdmin;
        this.locked = locked;
        this.enabled = enabled;
//        this.groupId = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singleton(authority);
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Roles> roles = getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (Roles role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//
//        return authorities;
//    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return isAdmin == user.isAdmin && locked == user.locked && enabled == user.enabled && Objects.equals(id, user.id) && username.equals(user.username) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && email.equals(user.email) && userRole == user.userRole && Objects.equals(groupId, user.groupId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, username, password, firstName, lastName, email, userRole, isAdmin, locked, enabled, groupId);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

//    public UserRole getUserRole() {
//        return userRole;
//    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

//    public Set<Roles> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Roles> roles) {
//        this.roles = roles;
//    }

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

//    public List<Long> getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(List<Long> groupId) {
//        this.groupId = groupId;
//    }
}
